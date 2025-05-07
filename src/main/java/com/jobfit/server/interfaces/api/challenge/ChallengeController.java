package com.jobfit.server.interfaces.api.challenge;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.challenge.ChallengeDetailInfo;
import com.jobfit.server.service.challenge.ChallengeInfo;
import com.jobfit.server.service.challenge.ChallengeListInfo;
import com.jobfit.server.service.challenge.ChallengeService;
import com.jobfit.server.support.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

	private final ChallengeService challengeService;

	@GetMapping("/api/v1/challenges")
	public ResponseEntity<ApiResponse<List<ChallengeListInfo>>> getChallenges(@AuthenticationPrincipal CustomUserDetails user) {
		return ApiResponse.OK(challengeService.getChallenges(user.getUserId()));
	}

	@GetMapping("/api/v1/challenge/{challengeId}")
	public ResponseEntity<ApiResponse<ChallengeDetailInfo>> getChallenges(@PathVariable Long challengeId) {
		return ApiResponse.OK(challengeService.getChallenge(challengeId));
	}

	@PostMapping("/api/v1/challenge")
	public ResponseEntity<ApiResponse<ChallengeInfo>> createChallenge(
		@AuthenticationPrincipal CustomUserDetails user,
		@RequestBody CreateChallengeRequest request
	) {
		return ApiResponse.CREATE(challengeService.createChallenge(request.toCommand(user.getUserId())));
	}

	@DeleteMapping("/api/v1/challenge/{challengeId}")
	public ResponseEntity<ApiResponse<ChallengeInfo>> deleteChallenge(
		@PathVariable Long challengeId
	) {
		return ApiResponse.OK(challengeService.deleteChallenge(challengeId));
	}
}
