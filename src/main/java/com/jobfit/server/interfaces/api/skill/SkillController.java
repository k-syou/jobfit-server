package com.jobfit.server.interfaces.api.skill;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.skill.SkillInfo;
import com.jobfit.server.service.skill.SkillService;
import com.jobfit.server.support.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SkillController {

	private final SkillService skillService;

	@GetMapping("/api/v1/user/skill")
	public ResponseEntity<ApiResponse<SkillInfo>> getSkill(@AuthenticationPrincipal CustomUserDetails user) {
		return ApiResponse.OK(skillService.getSkill(user.getUserId()));
	}

	@PatchMapping("/api/v1/user/skill")
	public ResponseEntity<ApiResponse<SkillInfo>> changeSkill(
		@AuthenticationPrincipal CustomUserDetails user,
		@RequestBody PatchSkillRequest request
	) {
		return ApiResponse.OK(skillService.patchSkill(request.toCommand(user.getUserId())));
	}
}
