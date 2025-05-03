package com.jobfit.server.service.trend;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TrendInfos {

	private List<TrendInfo> growth;
	private List<TrendInfo> stable;
	private List<TrendInfo> decline;

	@Builder
	public TrendInfos(List<TrendInfo> growth, List<TrendInfo> stable, List<TrendInfo> decline) {
		this.growth = growth;
		this.stable = stable;
		this.decline = decline;
	}
}
