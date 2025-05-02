package com.jobfit.server.interfaces.api.otp;

import com.jobfit.server.service.otp.OtpCreateCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OptCreateRequest {

	private String email;
	private String type;

	public OtpCreateCommand toCommand() {
		return new OtpCreateCommand(email, type);
	}
}
