package com.jobfit.server.interfaces.api.user;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.service.user.UserSignUpCommand;
import lombok.Getter;

@Getter
public class UserSignupRequest {

	private String email;
	private String username;
	private String password;
	private String confirmPassword;
	private String name;
	private String otp;

	public UserSignupRequest(String email, String username, String password, String confirmPassword, String name, String otp) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.name = name;
		this.otp = otp;
	}

	public UserSignUpCommand toCommand() {

		if (email == null || email.isEmpty()) {
			throw USER_SIGNUP_EMAIL_NULL_OR_EMPTY.exception();
		}

		if (username == null || username.isEmpty()) {
			throw USER_SIGNUP_USERNAME_NULL_OR_EMPTY.exception();
		}

		if (password == null || password.isEmpty()) {
			throw USER_SIGNUP_PASSWORD_NULL_OR_EMPTY.exception();
		}

		if (confirmPassword == null || confirmPassword.isEmpty()) {
			throw USER_SIGNUP_CONFIRM_PASSWORD_NULL_OR_EMPTY.exception();
		}

		if (!password.equals(confirmPassword)) {
			throw USER_SIGNUP_PASSWORD_NOT_MATCH.exception();
		}

		if (name == null || name.isEmpty()) {
			throw USER_SIGNUP_NAME_NULL_OR_EMPTY.exception();
		}

		if (otp == null || otp.isEmpty()) {
			throw USER_SIGNUP_OTP_NULL_OR_EMPTY.exception();
		}

		return new UserSignUpCommand(email, username, password, name, otp);
	}
}
