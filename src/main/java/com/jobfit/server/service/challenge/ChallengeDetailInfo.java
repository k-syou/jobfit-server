package com.jobfit.server.service.challenge;

import java.time.LocalDate;

import com.jobfit.server.infras.challenge.ChallengeDetailDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChallengeDetailInfo {

	private Long challengeId;
	private Long userId;
	private Long recruitId;
	private String title;
	private String companyName;
	private LocalDate registrationDate;
	private Long progress;
	private String careerType;
	private String strengths;
	private String weaknesses;
	private String content;

	@Builder
	private ChallengeDetailInfo(
		Long challengeId,
		Long userId,
		Long recruitId,
		String title,
		String companyName,
		LocalDate registrationDate,
		Long progress,
		String careerType,
		String strengths,
		String weaknesses,
		String content
	) {
		this.challengeId = challengeId;
		this.userId = userId;
		this.recruitId = recruitId;
		this.title = title;
		this.companyName = companyName;
		this.registrationDate = registrationDate;
		this.progress = progress;
		this.careerType = careerType;
		this.strengths = strengths;
		this.weaknesses = weaknesses;
		this.content = content;
	}

	public static ChallengeDetailInfo from(ChallengeDetailDto dto) {
		return ChallengeDetailInfo.builder()
			.challengeId(dto.getChallengeId())
			.userId(dto.getUserId())
			.recruitId(dto.getRecruitId())
			.title(dto.getTitle())
			.companyName(dto.getCompanyName())
			.registrationDate(dto.getRegistrationDate())
			.progress(dto.getProgress())
			.careerType(dto.getCareerType())
			.strengths(dto.getStrengths())
			.weaknesses(dto.getWeaknesses())
			.content(dto.getContent())
			.build();
	}
}
