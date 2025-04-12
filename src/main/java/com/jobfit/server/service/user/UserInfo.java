package com.jobfit.server.service.user;

import java.time.LocalDateTime;

import com.jobfit.server.domain.user.User;

import lombok.Builder;

public class UserInfo {

	private Long userId;
	private String username;
	private LocalDateTime createAt;

	@Builder
	private UserInfo(Long userId, String username, LocalDateTime createAt) {
		this.userId = userId;
		this.username = username;
		this.createAt = createAt;
	}

	public static UserInfo from(User user) {
		return UserInfo.builder()
			.userId(user.getId())
			.username(user.getUsername())
			.createAt(user.getCreatedAt())
			.build();
	}
}
