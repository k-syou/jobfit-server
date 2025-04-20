package com.jobfit.server.service.user;

import com.jobfit.server.domain.user.UserStatus;
import lombok.Getter;

@Getter
public class UserSignUpCommand {
	private String email;
	private String username;
	private String password;
	private String nickname;
	UserStatus status;
	public UserSignUpCommand(String email, String username, String password, String nickname, UserStatus status ) {
		this.email=email;
		this.username = username;
		this.password = password;
		this.nickname=nickname;
		this.status=status;
	}
}
