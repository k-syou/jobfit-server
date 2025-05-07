package com.jobfit.server.service.challenge;

import lombok.Getter;

@Getter
public class CreateChallengeCommand {
	private Long userId;
	private Long recruitId;

	public CreateChallengeCommand(Long userId, Long recruitId) {
		this.userId = userId;
		this.recruitId = recruitId;
	}
}
