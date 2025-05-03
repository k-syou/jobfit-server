package com.jobfit.server.service.trend;

import org.springframework.data.domain.Pageable;

import lombok.Getter;

@Getter
public class GetTrendsCommand {

	private Pageable pageable;
	private String searchKey;

	public GetTrendsCommand(Pageable pageable, String searchKey) {
		this.pageable = pageable;
		this.searchKey = searchKey;
	}
}
