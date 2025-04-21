package com.jobfit.server.interfaces.api.user;

import com.jobfit.server.service.user.UserCheckDuplicatedEmailCommand;
import com.jobfit.server.service.user.UserWithDrawCommand;
import com.jobfit.server.support.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.user.UserInfo;
import com.jobfit.server.service.user.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<UserInfo>> signup(@RequestBody UserSignupRequest request) {
		return ApiResponse.OK(userService.signUp(request.toCommand()));
	}
	@PostMapping("/checkemail")
	public ResponseEntity<?> checkEmail(@RequestBody @Validated UserCheckDuplicatedEmailRequest request)
	{
		boolean exists=userService.isEmailDuplicated(request.toCommand());
		return ApiResponse.OK(Map.of("duplicated",exists));
	}
	// 유저에 대한 접근은 시큐리티에 커스텀 유저로 접근해야 되기 때문에 request 불필요 ->
	//@AuthenticationPrincipal 이걸로 인가된 사용자인지 시큐리티 자체적으로 검증해주기 대문에 커스텀 유저로 접근해서
	// 체크해야됨
	@PatchMapping("/withdraw")
	public ResponseEntity<?> withDrawUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
		String email = userDetails.getUsername();

		userService.withDrawUser(new UserWithDrawCommand(email));
		return ApiResponse.OK(Map.of("message", "회원 탈퇴가 완료되었습니다."));
	}

}


