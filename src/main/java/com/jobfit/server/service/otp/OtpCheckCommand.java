package com.jobfit.server.service.otp;

import com.jobfit.server.domain.otp.OtpType;

import lombok.Getter;

@Getter
public class OtpCheckCommand {
	private String email;
	private OtpType type;
	private String otp;

	public OtpCheckCommand(String email, String type, String otp) {
		this.email = email;
		this.type = OtpType.from(type);
		this.otp = otp;
	}
}
