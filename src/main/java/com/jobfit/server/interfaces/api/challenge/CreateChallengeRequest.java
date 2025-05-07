package com.jobfit.server.interfaces.api.challenge;

import com.jobfit.server.service.challenge.CreateChallengeCommand;

import lombok.Getter;

@Getter
public class CreateChallengeRequest {

	private Long recruitId;

	public CreateChallengeRequest(Long recruitId) {
		this.recruitId = recruitId;
	}

	public CreateChallengeCommand toCommand(Long userId) {
		return new CreateChallengeCommand(userId, recruitId);
	}
}
