package com.jobfit.server.service.user;

import lombok.Getter;

@Getter
public class UserSignUpCommand {

	private String email;
	private String username;
	private String password;
	private String name;
	private String otp;

	public UserSignUpCommand(String email, String username, String password, String name, String otp) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.otp = otp;
	}
}
