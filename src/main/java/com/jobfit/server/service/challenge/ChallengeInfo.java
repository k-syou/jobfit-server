package com.jobfit.server.service.challenge;

import java.time.LocalDateTime;

import com.jobfit.server.domain.challenge.Challenge;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChallengeInfo {
	private Long challengeId;
	private Long userId;
	private Long recruitId;
	private LocalDateTime createdAt;

	@Builder
	private ChallengeInfo(Long challengeId, Long userId, Long recruitId, LocalDateTime createdAt) {
		this.challengeId = challengeId;
		this.userId = userId;
		this.recruitId = recruitId;
		this.createdAt = createdAt;
	}

	public static ChallengeInfo from(Challenge challenge) {
		return ChallengeInfo.builder()
			.challengeId(challenge.getId())
			.userId(challenge.getUserId())
			.recruitId(challenge.getRecruitId())
			.createdAt(challenge.getCreatedAt())
			.build();
	}
}
