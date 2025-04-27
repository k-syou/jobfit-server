package com.jobfit.server.service.user;

import lombok.Getter;

@Getter
public class UserSignUpCommand {
	private String email;
	private String username;
	private String password;
	private String nickname;
	public UserSignUpCommand(String email, String username, String password, String nickname) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
}
