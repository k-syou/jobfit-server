package com.jobfit.server.interfaces.api.recruit;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.recruit.RecruitDetailInfo;
import com.jobfit.server.service.recruit.RecruitInfo;
import com.jobfit.server.service.recruit.RecruitService;

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
	public ResponseEntity<ApiResponse<Page<RecruitInfo>>> searchRecruits(
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false, defaultValue = "최신 등록일 순") String sortType,
			@RequestParam(required = false) Integer size,
			@RequestParam(required = false) String companyName,
			@RequestParam(required = false) String region,
			@RequestParam(required = false) String job,
			@RequestParam(required = false) String careerType) {

		Page<RecruitInfo> recruitPage = recruitService.searchRecruits(
				page,
				sortType,
				size,
				companyName,
				region,
				job,
				careerType);

		return ApiResponse.OK(recruitPage);
	}

	@PostMapping("/testDataInput")
	public ResponseEntity<ApiResponse<String>> inputTestData(
			@RequestParam(defaultValue = "1") int start,
			@RequestParam(defaultValue = "10") int end) {
		try {
			int savedCount = recruitService.saveTestDataFromApi(start, end);
			String message = "Successfully saved " + savedCount + " recruits.";
			return ApiResponse.OK(message);
		} catch (Exception e) {
			return ApiResponse.BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Failed to input test data: " + e.getMessage());
		}
	}
}
