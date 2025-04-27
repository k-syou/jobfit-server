package com.jobfit.server.service.otp;

import static com.jobfit.server.support.exception.BusinessError.*;

import lombok.Getter;

@Getter
public class OtpCreateCommand {
	private String email;

	public OtpCreateCommand(String email) {

		if (email == null || email.isEmpty()) {
			throw MAIL_SEND_EMAIL_NULL_OR_EMPTY.exception();
		}

		this.email = email;
	}
}
