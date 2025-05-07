package com.jobfit.server.interfaces.scheduler.recruit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jobfit.server.service.recruit.RecruitService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecruitScheduler {

	private final RecruitService recruitService;

	@Scheduled(cron = "0 0 11 * * *")
	public void runRecruitJob() {
		recruitService.schedulerRecruit();
	}
}
