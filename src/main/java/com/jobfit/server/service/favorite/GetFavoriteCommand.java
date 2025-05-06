package com.jobfit.server.service.favorite;

import lombok.Getter;

@Getter
public class GetFavoriteCommand {

	private Long userId;

	public GetFavoriteCommand(Long userId) {
		this.userId = userId;
	}
}
