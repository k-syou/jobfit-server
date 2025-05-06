package com.jobfit.server.interfaces.api.favorite;

import com.jobfit.server.service.favorite.FavoriteCommand;

import lombok.Getter;

@Getter
public class FavoriteRequest {
	private Long recruitId;

	public FavoriteRequest(Long recruitId) {
		this.recruitId = recruitId;
	}

	public FavoriteCommand toCommand(Long userId) {
		return new FavoriteCommand(userId, recruitId);
	}
}
