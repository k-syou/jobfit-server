package com.jobfit.server.interfaces.api.user;

import com.jobfit.server.interfaces.api.user.request.FindPasswordRequest;
import com.jobfit.server.interfaces.api.user.request.FindUsernameRequest;
import com.jobfit.server.interfaces.api.user.request.UserCheckDuplicatedEmailRequest;
import com.jobfit.server.interfaces.api.user.request.UserEditRequest;
import com.jobfit.server.interfaces.api.user.request.UserSignupRequest;
import com.jobfit.server.service.user.UserWithDrawCommand;
import com.jobfit.server.support.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.user.UserInfo;
import com.jobfit.server.service.user.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<UserInfo>> signup(@RequestBody UserSignupRequest request) {
		return ApiResponse.OK(userService.signUp(request.toCommand()));
	}
	@PostMapping("/check/username")
	public ResponseEntity<ApiResponse<Void>> checkEmail(@RequestBody UserCheckDuplicatedEmailRequest request) {
		userService.isUsernameDuplicated(request.toCommand());
		return ApiResponse.OK("가입할 수 있는 아이디입니다.", null);
	}
	// 유저에 대한 접근은 시큐리티에 커스텀 유저로 접근해야 되기 때문에 request 불필요 ->
	//@AuthenticationPrincipal 이걸로 인가된 사용자인지 시큐리티 자체적으로 검증해주기 대문에 커스텀 유저로 접근해서
	// 체크해야됨
	@PatchMapping("/withdraw")
	public ResponseEntity<ApiResponse<Void>> withDrawUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
		Long userId = userDetails.getUserId();

		userService.withDrawUser(new UserWithDrawCommand(userId));

		return ApiResponse.OK(null);
	}

	@GetMapping("/profile")
	public ResponseEntity<ApiResponse<UserInfo>> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
		return ApiResponse.OK(userService.getProfile(userDetails.getUserId()));
	}

	@PatchMapping("/profile")
	public ResponseEntity<ApiResponse<UserInfo>> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody UserEditRequest request) {
		return ApiResponse.OK(userService.editProfile(request.toCommand(userDetails.getUserId())));
	}

	@PostMapping("/find-username")
	public ResponseEntity<ApiResponse<Void>> findUsername(@RequestBody FindUsernameRequest request) {
		userService.findUsername(request.toCommand());
		return ApiResponse.OK(null);
	}

	@PostMapping("/find-password")
	public ResponseEntity<ApiResponse<Void>> findPassword(@RequestBody FindPasswordRequest request) {
		userService.findPassword(request.toCommand());
		return ApiResponse.OK(null);
	}

}


