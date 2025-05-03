package com.jobfit.server.interfaces.api.trend;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.jobfit.server.service.trend.GetTrendsCommand;

import lombok.Getter;

@Getter
public class GetTrendsRequest {
	private int page;
	private int size;
	private String searchKey;

	public GetTrendsRequest(int page, int size, String searchKey) {
		this.page = page;
		this.size = size;
		this.searchKey = searchKey;
	}

	public GetTrendsCommand toCommand() {
		Pageable pageable = PageRequest.of(page, size);
		return new GetTrendsCommand(pageable, searchKey);
	}
}
