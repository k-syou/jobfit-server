package com.jobfit.server.interfaces.api.challenge;

import lombok.Getter;

@Getter
public class DeleteChallengeRequest {

	private Long challengeId;

	public DeleteChallengeRequest(Long challengeId) {
		this.challengeId = challengeId;
	}
}
