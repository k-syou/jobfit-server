package com.jobfit.server.service.user;

import com.jobfit.server.domain.otp.OtpType;

import lombok.Getter;

@Getter
public class FindUsernameCommand {

	private String email;
	private OtpType type;
	private String otp;

	public FindUsernameCommand(String email, String type, String otp) {
		this.email = email;
		this.type = OtpType.from(type);
		this.otp = otp;
	}
}
