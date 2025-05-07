package com.jobfit.server.service.recruit;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jobfit.server.domain.recruit.Recruit;
import com.jobfit.server.domain.recruit.RecruitRepository;
import com.jobfit.server.infras.recruit.RecruitJpaRepository;
import com.jobfit.server.support.recurit.RecruitSupport;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitService {

	private final RecruitRepository recruitRepository;
	private final RecruitJpaRepository recruitJpaRepository;
	private final RecruitSupport recruitSupport;

	@Transactional
	public RecruitDetailInfo getRecruit(Long recruitId) {
		return RecruitDetailInfo
				.from(recruitRepository.findById(recruitId).orElseThrow(() -> new RuntimeException("Recruit not found")));
	}

	@Transactional(readOnly = true)
	public Page<RecruitInfo> searchRecruits(
			Integer page,
			String sortType,
			Integer size,
			String companyName,
			String region,
			String job,
			String careerType) {
		Sort sort;
		if ("마감일순".equals(sortType)) {
			sort = Sort.by(Sort.Direction.ASC, "endDate");
		} else {
			sort = Sort.by(Sort.Direction.DESC, "registerDate");
		}

		int pageNumber = (page != null && page >= 0) ? page : 0;
		int pageSize = (size != null && size > 0) ? size : 10;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Recruit> recruitPage = recruitJpaRepository.findRecruits(
				companyName,
				region,
				job,
				careerType,
				pageable);

		return recruitPage.map(RecruitInfo::from);
	}

	private static final Logger log = LoggerFactory.getLogger(RecruitService.class);
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Transactional // 데이터를 저장하므로 Transactional 추가
	public int schedulerRecruit() {
		int savedTotal = 0;
		try {
			// 1. 처음 요청해서 전체 개수 확인
			JSONObject firstResponse = recruitSupport.getJobInfo(1, 1);
			JSONObject getJobInfo = firstResponse.getJSONObject("GetJobInfo");
			int totalCount = getJobInfo.getInt("list_total_count");

			int pageSize = 1000;

			// 2. 1000개 단위로 반복 요청
			for (int start = 1; start <= totalCount; start += pageSize) {
				int end = Math.min(start + pageSize - 1, totalCount);
				JSONObject response = recruitSupport.getJobInfo(start, end);
				List<Recruit> recruits = parseRecruitsFromApi(response);

				if (!recruits.isEmpty()) {
					recruitJpaRepository.saveAll(recruits);
					savedTotal += recruits.size();
					log.info("Saved {} recruits ({} ~ {})", recruits.size(), start, end);
				} else {
					log.warn("No recruits to save for page {} ~ {}", start, end);
				}
			}

		} catch (Exception e) {
			log.error("Error saving recruit data from API: {}", e.getMessage(), e);
		}

		return savedTotal;
	}

	private List<Recruit> parseRecruitsFromApi(JSONObject responseJson) {
		List<Recruit> recruitsToSave = new ArrayList<>();

		if (!responseJson.has("GetJobInfo")) return recruitsToSave;

		JSONObject getJobInfo = responseJson.getJSONObject("GetJobInfo");
		if (!getJobInfo.has("row")) return recruitsToSave;

		JSONArray jobArray = getJobInfo.getJSONArray("row");

		for (int i = 0; i < jobArray.length(); i++) {
			JSONObject item = jobArray.getJSONObject(i);

			String category = item.optString("JOBCODE_NM", "");
			String companyName = item.optString("CMPNY_NM", "");
			String summary = item.optString("BSNS_SUMRY_CN", "");
			String recruitNumber = item.optString("RCRIT_NMPR_CO", "");
			String educationType = item.optString("ACDMCR_NM", "");
			String jobType = item.optString("EMPLYM_STLE_CMMN_MM", "");
			String workPlace = item.optString("WORK_PARAR_BASS_ADRES", "");
			String content = item.optString("DTY_CN", "");
			String careerType = item.optString("CAREER_CND_NM", "");
			String wage = item.optString("HOPE_WAGE", "");
			String workTime = item.optString("WORK_TIME_NM", "");
			String workType = item.optString("WORK_TM_NM", "");
			String workSchedule = item.optString("HOLIDAY_NM", "");
			String totalTime = item.optString("WEEK_WORK_HR", "");
			String insurance = item.optString("JO_FEINSR_SBSCRB_NM", "");
			String recruitmentMethod = item.optString("MODEL_MTH_NM", "");
			String applyMethod = item.optString("RCEPT_MTH_NM", "");
			String applyDocument = item.optString("PRESENTN_PAPERS_NM", "");
			String manager = item.optString("MNGR_NM", "");
			String managerPhonenumber = item.optString("MNGR_PHON_NO", "");
			String managerOrganization = item.optString("MNGR_INSTT_NM", "");
			String companyAddress = item.optString("BASS_ADRES_CN", "");
			String title = item.optString("JO_SJ", "");
			String jobPosting = item.optString("GUI_LN", "");
			String regDateStr = item.optString("JO_REG_DT", "");
			String endDateStr = item.optString("RCEPT_CLOS_NM", "").replaceAll("[^0-9-]", "").trim();

			LocalDate registerDate = parseDate(regDateStr);
			LocalDate endDate = parseDate(endDateStr);

			Recruit recruit = new Recruit(
				category,
				companyName,
				summary,
				recruitNumber,
				educationType,
				jobType,
				workPlace,
				content,
				careerType,
				wage,
				workTime,
				workType,
				workSchedule,
				totalTime,
				insurance,
				recruitmentMethod,
				applyMethod,
				applyDocument,
				manager,
				managerPhonenumber,
				managerOrganization,
				companyAddress,
				title,
				jobPosting,
				registerDate,
				endDate
			);

			recruitsToSave.add(recruit);
		}

		return recruitsToSave;
	}

	// 날짜 문자열 파싱 헬퍼 메소드
	private LocalDate parseDate(String dateStr) {
		if (dateStr == null || dateStr.isEmpty()) {
			return null;
		}
		try {
			// API에서 제공하는 날짜 형식이 "yyyy.MM.dd"가 아니면 DATE_FORMATTER 수정 필요
			return LocalDate.parse(dateStr, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			log.warn("Could not parse date string: {}", dateStr, e);
			return null; // 파싱 실패 시 null 반환
		}
	}
}
