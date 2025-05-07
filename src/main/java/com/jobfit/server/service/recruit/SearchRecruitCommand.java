package com.jobfit.server.service.recruit;

import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchRecruitCommand {

	private Long userId;
	private String companyName;
	private String region;
	private String job;
	private String careerType;
	private Pageable pageable;

	public SearchRecruitCommand(Long userId, String companyName, String region, String job, String careerType, Pageable pageable) {
		this.userId = userId;
		this.companyName = companyName;
		this.region = region;
		this.job = job;
		this.careerType = careerType;
		this.pageable = pageable;
	}

}
