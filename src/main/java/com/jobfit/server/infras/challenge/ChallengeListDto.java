package com.jobfit.server.infras.challenge;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ChallengeListDto {

	private Long challengeId;
	private Long userId;
	private Long recruitId;
	private String title;
	private String companyName;
	private LocalDate registrationDate;
	private Long progress;

	public ChallengeListDto(Long challengeId, Long userId, Long recruitId, String title, String companyName,
		LocalDate registrationDate, Long progress) {
		this.challengeId = challengeId;
		this.userId = userId;
		this.recruitId = recruitId;
		this.title = title;
		this.companyName = companyName;
		this.registrationDate = registrationDate;
		this.progress = progress;
	}
}
