package com.jobfit.server.support.event.otp;

import jakarta.mail.internet.MimeMessage;
import lombok.Getter;

@Getter
public class OtpMailEvent {
	private final MimeMessage message;

	public OtpMailEvent(MimeMessage message) {
		this.message = message;
	}
}
