package com.jobfit.server.service.recruit;

import java.time.LocalDate;

import com.jobfit.server.domain.recruit.Recruit;

import lombok.Builder;

public class RecruitInfo {
  private Long recruitId;
	private String title;  // 공고 이름
	private String companyName;  // 회사 이름
	private String workType;  // 근무 형태
	private String workPlace;  // 근무예정지
	private LocalDate registerDate;  // 등록일
	private LocalDate endDate;  // 마감일


	@Builder
	private RecruitInfo(Long recruitId, String title, String companyName, String workType, String workPlace, LocalDate registerDate, LocalDate endDate) {
		this.recruitId = recruitId;
		this.title = title;
		this.companyName = companyName;
		this.workType = workType;
		this.workPlace = workPlace;
		this.registerDate = registerDate;
		this.endDate = endDate;
	}

	public static RecruitInfo from(Recruit recruit) {
		return RecruitInfo.builder()
			.recruitId(recruit.getId())
			.title(recruit.getTitle())
			.companyName(recruit.getCompanyName())
			.workType(recruit.getWorkType())
			.workPlace(recruit.getWorkPlace())
			.registerDate(recruit.getRegisterDate())
			.endDate(recruit.getEndDate())
			.build();
	}
}
