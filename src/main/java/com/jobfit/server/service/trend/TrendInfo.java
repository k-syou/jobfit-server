package com.jobfit.server.service.trend;

import com.jobfit.server.domain.trend.Trend;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TrendInfo {

	private String name;
	private String industry;
	private String potential;
	private String type;

	@Builder
	private TrendInfo(String name, String industry, String potential, String type) {
		this.name = name;
		this.industry = industry;
		this.potential = potential;
		this.type = type;
	}

	public static TrendInfo from(Trend trend) {
		return TrendInfo.builder()
			.industry(trend.getIndustry())
			.potential(trend.getPotential())
			.name(trend.getName())
			.type(trend.getType().name())
			.build();
	}
}
