package com.jobfit.server.interfaces.api.user.request;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.service.user.FindUsernameCommand;
import com.jobfit.server.support.exception.BusinessError;

import lombok.Getter;

@Getter
public class FindUsernameRequest {
	private String email;
	private String type;
	private String otp;

	public FindUsernameRequest(String email, String type, String otp) {
		this.email = email;
		this.type = type;
		this.otp = otp;
	}

	public FindUsernameCommand toCommand() {

		if (email == null || email.isEmpty()) {
			throw USER_SIGNUP_EMAIL_NULL_OR_EMPTY.exception();
		}

		if (type == null || type.isEmpty()) {
			throw MAIL_SEND_TYPE_NULL_OR_EMPTY.exception();
		}

		if (otp == null || otp.isEmpty()) {
			throw USER_SIGNUP_OTP_NOT_FOUND.exception();
		}

		return new FindUsernameCommand(email, type, otp);
	}
}
