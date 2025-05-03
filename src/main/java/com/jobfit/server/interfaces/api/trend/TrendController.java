package com.jobfit.server.interfaces.api.trend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.trend.TrendInfo;
import com.jobfit.server.service.trend.TrendInfos;
import com.jobfit.server.service.trend.TrendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TrendController {

	private final TrendService trendService;

	@GetMapping("/api/v1/trends")
	public ResponseEntity<ApiResponse<TrendInfos>> getTrends(GetTrendsRequest request) {
		return ApiResponse.OK(trendService.getTrends(request.toCommand()));
	}
}
