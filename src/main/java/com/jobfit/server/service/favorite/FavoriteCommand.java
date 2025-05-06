package com.jobfit.server.service.favorite;

import lombok.Getter;

@Getter
public class FavoriteCommand {
	private Long userId;
	private Long recruitId;

	public FavoriteCommand(Long userId, Long recruitId) {
		this.userId = userId;
		this.recruitId = recruitId;
	}
}
