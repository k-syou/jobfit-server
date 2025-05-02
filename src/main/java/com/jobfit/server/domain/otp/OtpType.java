package com.jobfit.server.domain.otp;

import static com.jobfit.server.support.exception.BusinessError.*;

public enum OtpType {
	SIGNUP,
	USERNAME,
	PASSWORD;

	public static OtpType from(String value) {
		try {
			return OtpType.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw MAIL_SEND_TYPE_NULL_OR_EMPTY.exception();
		}
	}
}
