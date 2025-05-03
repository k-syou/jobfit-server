package com.jobfit.server.service.user;

import lombok.Getter;

@Getter
public class UserEditCommand {
	private Long userId;
	private String newPassword;

	public UserEditCommand(Long userId, String newPassword) {
		this.userId = userId;
		this.newPassword = newPassword;
	}

}
