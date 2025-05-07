package com.jobfit.server.service.challenge;

import java.time.LocalDate;

import com.jobfit.server.infras.challenge.ChallengeListDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChallengeListInfo {

	private Long challengeId;
	private Long userId;
	private Long recruitId;
	private String title;
	private String companyName;
	private LocalDate registrationDate;
	private Long progress;

	@Builder
	private ChallengeListInfo(Long challengeId, Long userId, Long recruitId, String title, String companyName, LocalDate registrationDate, Long progress) {
		this.challengeId = challengeId;
		this.userId = userId;
		this.recruitId = recruitId;
		this.title = title;
		this.companyName = companyName;
		this.registrationDate = registrationDate;
		this.progress = progress;
	}

	public static ChallengeListInfo from(ChallengeListDto dto) {
		return ChallengeListInfo.builder()
			.challengeId(dto.getChallengeId())
			.userId(dto.getUserId())
			.recruitId(dto.getRecruitId())
			.title(dto.getTitle())
			.companyName(dto.getCompanyName())
			.registrationDate(dto.getRegistrationDate())
			.progress(dto.getProgress())
			.build();
	}
}
