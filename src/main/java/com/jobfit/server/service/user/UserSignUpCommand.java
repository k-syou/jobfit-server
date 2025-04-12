package com.jobfit.server.service.user;

import lombok.Getter;

@Getter
public class UserSignUpCommand {

	private String username;
	private String password;

	public UserSignUpCommand(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
