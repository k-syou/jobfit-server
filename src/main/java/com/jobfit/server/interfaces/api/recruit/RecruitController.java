package com.jobfit.server.interfaces.api.recruit;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.recruit.RecruitDetailInfo;
import com.jobfit.server.service.recruit.RecruitInfo;
import com.jobfit.server.service.recruit.RecruitService;
import com.jobfit.server.support.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruit")
public class RecruitController {

	private final RecruitService recruitService;

	@GetMapping("/{recruitId}")
	public ResponseEntity<ApiResponse<RecruitDetailInfo>> getRecruit(@PathVariable Long recruitId) {
		try {
			RecruitDetailInfo recruitDetailInfo = recruitService.getRecruit(recruitId);
			return ApiResponse.OK(recruitDetailInfo);
		} catch (Exception e) {
			return ApiResponse.BusinessException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<RecruitInfo>>> searchRecruits(@AuthenticationPrincipal CustomUserDetails user, @ModelAttribute SearchRecruitRequest request) {
		Long userId = (user != null) ? user.getUserId() : null;
		return ApiResponse.OK(recruitService.searchRecruits(request.toCommand(userId)));
	}

	@PostMapping("/testDataInput")
	public ResponseEntity<ApiResponse<String>> inputTestData(
			@RequestParam(defaultValue = "1") int start,
			@RequestParam(defaultValue = "10") int end) {
		try {
			int savedCount = recruitService.schedulerRecruit();
			String message = "Successfully saved " + savedCount + " recruits.";
			return ApiResponse.OK(message);
		} catch (Exception e) {
			return ApiResponse.BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Failed to input test data: " + e.getMessage());
		}
	}
}
