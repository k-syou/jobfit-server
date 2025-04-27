package com.jobfit.server.service.otp;

import lombok.Getter;

@Getter
public class OtpCheckCommand {
	private String email;
	private String otp;

	public OtpCheckCommand(String email, String otp) {
		this.email = email;
		this.otp = otp;
	}
}
