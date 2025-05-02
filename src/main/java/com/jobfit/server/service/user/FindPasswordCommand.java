package com.jobfit.server.service.user;

import com.jobfit.server.domain.otp.OtpType;

import lombok.Getter;

@Getter
public class FindPasswordCommand {

	private String username;
	private String email;
	private OtpType type;
	private String otp;

	public FindPasswordCommand(String username, String email, String type, String otp) {
		this.username = username;
		this.email = email;
		this.type = OtpType.from(type);
		this.otp = otp;
	}
}
