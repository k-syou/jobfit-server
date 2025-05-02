package com.jobfit.server.interfaces.api.otp;

import static com.jobfit.server.support.exception.BusinessError.*;

import com.jobfit.server.service.otp.OtpCheckCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OtpCheckRequest {

	private String email;
	private String type;
	private String otp;

	public OtpCheckCommand toCommand() {

		if (email == null || email.isEmpty()) {
			throw MAIL_SEND_EMAIL_NULL_OR_EMPTY.exception();
		}

		if (otp == null || otp.isEmpty()) {
			throw CHECK_OTP_NULL_OR_EMPTY.exception();
		}

		if (type == null || type.isEmpty()) {
			throw MAIL_SEND_TYPE_NULL_OR_EMPTY.exception();
		}

		return new OtpCheckCommand(email, type, otp);
	}

}
