package com.jobfit.server.interfaces.api.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.user.UserInfo;
import com.jobfit.server.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/api/v1/signup")
	public ResponseEntity<ApiResponse<UserInfo>> signup(@RequestBody UserSignupRequest request) {
		return ApiResponse.OK(userService.signUp(request.toCommand()));
	}
}
