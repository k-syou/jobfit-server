package com.jobfit.server.service.recruit;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
	private final RecruitSupport recruitSuppot;

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
	public int saveTestDataFromApi(int start, int end) {
		int savedCount = 0;
		try {
			// 1. 외부 API 호출
			JSONObject responseJson = recruitSuppot.getJobInfo(start, end);

			// 2. JSON 파싱 및 Recruit 엔티티 생성
			// 실제 API 응답 구조에 따라 키("GetJobInfo", "row")를 조정해야 합니다.
			if (responseJson.has("GetJobInfo")) {
				JSONObject getJobInfo = responseJson.getJSONObject("GetJobInfo");
				if (getJobInfo.has("row")) {
					JSONArray jobArray = getJobInfo.getJSONArray("row");
					List<Recruit> recruitsToSave = new ArrayList<>();

					for (int i = 0; i < jobArray.length(); i++) {
						JSONObject item = jobArray.getJSONObject(i);
						// JSON 필드명은 API 명세에 따라 정확히 지정해야 합니다. (예시 필드명 사용)
						String category = item.optString("JOBCODE_NM"); // 직무 분야
						String companyName = item.optString("CMPNY_NM"); // 기업 명칭
						String summary = item.optString("BSNS_SUMRY_CN", ""); // 사업 요약 내용
						String recruitNumber = item.optString("RCRIT_NMPR_CO", ""); // 모집 인원
						String educationType = item.optString("ACDMCR_NM", ""); // 학력코드 명
						String jobType = item.optString("EMPLYM_STLE_CMMN_MM", ""); // 고용형태코드명
						String workPlace = item.optString("WORK_PARAR_BASS_ADRES", ""); // 근무지
						String content = item.optString("DTY_CN", ""); // 직무 내용
						String careerType = item.optString("CAREER_CND_NM", ""); // 경력조건코드명
						String wage = item.optString("HOPE_WAGE", ""); // 급여 조건
						String workTime = item.optString("WORK_TIME_NM", ""); // 근무 시간
						String workType = item.optString("WORK_TM_NM", ""); // 근무 형태
						String workSchedule = item.optString("HOLIDAY_NM", ""); // 주 근무 일수
						String totalTime = item.optString("WEEK_WORK_HR", ""); // 주당 근무 시간
						String insurance = item.optString("JO_FEINSR_SBSCRB_NM", ""); // 4대 보험
						String recruitmentMethod = item.optString("MODEL_MTH_NM", ""); // 전형 방법
						String applyMethod = item.optString("RCEPT_MTH_NM", ""); // 접수 방법
						String applyDocument = item.optString("PRESENTN_PAPERS_NM", ""); // 제출 서류
						String manager = item.optString("MNGR_NM", ""); // 담당자 이름
						String managerPhonenumber = item.optString("MNGR_PHON_NO", ""); // 담당자 전화번호
						String managerOrganization = item.optString("MNGR_INSTT_NM", ""); // 담당자 소속기관 명
						String companyAddress = item.optString("BASS_ADRES_CN", ""); // 기업 주소
						String title = item.optString("JO_SJ", ""); // 공고제목
						String jobPosting = item.optString("GUI_LN", ""); // 모집 요강
						String regDateStr = item.optString("JO_REG_DT", ""); // 등록일 문자열
						String endDateStr = item.optString("RCEPT_CLOS_NM", "").replaceAll("[^0-9-]", "").trim(); // 마감일 문자열에서 날짜만
																																																			// 추출

						LocalDate registerDate = null;
						LocalDate endDate = null;

						// 날짜 파싱 (오류 발생 시 null 처리 또는 로그 기록)
						if (regDateStr != null && !regDateStr.isEmpty()) {
							registerDate = parseDate(regDateStr);
						}
						if (endDateStr != null && !endDateStr.isEmpty()) {
							endDate = parseDate(endDateStr);
						}

						// Recruit 엔티티 생성 (API에 없는 필드는 null 또는 기본값으로 채움)
						// Recruit 생성자 파라미터 순서 및 타입 확인 필요
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
								endDate);
						recruitsToSave.add(recruit);
					}

					// 4. DB에 저장
					if (!recruitsToSave.isEmpty()) {
						recruitJpaRepository.saveAll(recruitsToSave);
						savedCount = recruitsToSave.size();
						log.info("Successfully saved {} recruits from API.", savedCount);
					} else {
						log.info("No valid recruit data found in the API response to save.");
					}
				} else {
					log.warn("API response does not contain 'row' field within 'GetJobInfo'.");
				}
			} else {
				log.warn("API response does not contain 'GetJobInfo' field.");
			}
		} catch (Exception e) {
			// API 호출 실패, JSON 파싱 오류, DB 저장 오류 등 처리
			log.error("Error saving test data from API: {}", e.getMessage(), e);
			// 필요에 따라 예외를 다시 던지거나 다른 방식으로 처리
			// throw new RuntimeException("Failed to save test data from API.", e);
		}
		return savedCount;
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
