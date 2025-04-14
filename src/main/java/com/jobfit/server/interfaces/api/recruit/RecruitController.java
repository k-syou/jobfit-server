package com.jobfit.server.interfaces.api.recruit;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.service.recruit.RecruitInfo;
import com.jobfit.server.service.recruit.RecruitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecruitController {

	private final RecruitService recruitService;
	@GetMapping("/api/v1/recruit/recent")
	public List<RecruitInfo> getRecentRecruits() {
		return recruitService.getRecentRecruits();
	}

	@GetMapping("/api/v1/recruit/end")
	public List<RecruitInfo> getEndRecruits() {
		return recruitService.getEndRecruits();
	}
	
}
