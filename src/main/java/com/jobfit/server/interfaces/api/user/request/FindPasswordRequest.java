package com.jobfit.server.interfaces.api.user.request;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.service.user.FindPasswordCommand;
import com.jobfit.server.service.user.FindUsernameCommand;

import lombok.Getter;

@Getter
public class FindPasswordRequest {
	private String username;
	private String email;
	private String type;
	private String otp;

	public FindPasswordRequest(String username, String email, String type, String otp) {
		this.username = username;
		this.email = email;
		this.type = type;
		this.otp = otp;
	}

	public FindPasswordCommand toCommand() {

		if (username == null || username.isEmpty()) {
			throw USER_SIGNUP_USERNAME_NULL_OR_EMPTY.exception();
		}

		if (email == null || email.isEmpty()) {
			throw USER_SIGNUP_EMAIL_NULL_OR_EMPTY.exception();
		}

		if (type == null || type.isEmpty()) {
			throw MAIL_SEND_TYPE_NULL_OR_EMPTY.exception();
		}

		if (otp == null || otp.isEmpty()) {
			throw USER_SIGNUP_OTP_NOT_FOUND.exception();
		}

		return new FindPasswordCommand(username, email, type, otp);
	}
}
