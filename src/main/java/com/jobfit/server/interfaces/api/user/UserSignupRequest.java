package com.jobfit.server.interfaces.api.user;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.service.user.UserSignUpCommand;

public class UserSignupRequest {

	private String username;
	private String password;
	private String confirmPassword;

	public UserSignUpCommand toCommand() {

		if (!password.equals(confirmPassword)) {
			throw USER_SIGNUP_PASSWORD_NOT_MATCH.exception();
		}

		return new UserSignUpCommand(username, password);
	}
}
