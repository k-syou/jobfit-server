package com.jobfit.server.infras.challenge;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ChallengeDetailDto {
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

	public ChallengeDetailDto(Long challengeId, Long userId, Long recruitId, String title, String companyName,
		LocalDate registrationDate, Long progress, String careerType, String strengths, String weaknesses, String content) {
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
}
