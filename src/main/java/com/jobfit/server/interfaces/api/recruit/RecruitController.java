package com.jobfit.server.interfaces.api.recruit;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.recruit.RecruitInfo;
import com.jobfit.server.service.recruit.RecruitDetailInfo;
import com.jobfit.server.service.recruit.RecruitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecruitController {

	private final RecruitService recruitService;
	@GetMapping("/api/v1/recruit/recent")
	public ResponseEntity<ApiResponse<List<RecruitInfo>>> getRecentRecruits() {
		return ApiResponse.OK(recruitService.getRecentRecruits());
	}

	@GetMapping("/api/v1/recruit/end")
	public ResponseEntity<ApiResponse<List<RecruitInfo>>> getEndRecruits() {
		return ApiResponse.OK(recruitService.getEndRecruits());
	}

	@GetMapping("/api/v1/recruit/{recruitId}")
	public ResponseEntity<ApiResponse<RecruitDetailInfo>> getRecruit(@PathVariable Long recruitId) {
		return ApiResponse.OK(recruitService.getRecruit(recruitId));
	}
}
