package com.jobfit.server.interfaces.api.otp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.otp.OtpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OtpController {

	private final OtpService mailService;

	@PostMapping("/api/v1/otp")
	public ResponseEntity<ApiResponse<Void>> otpCreate(@RequestBody OptCreateRequest request) {

		mailService.otpCreate(request.toCommand());

		return ApiResponse.OK(null);
	}

	@PostMapping("/api/v1/otp/check")
	public ResponseEntity<ApiResponse<Void>> otpCheck(@RequestBody OtpCheckRequest request) {

		mailService.checkOtp(request.toCommand());

		return ApiResponse.OK(null);
	}
}
