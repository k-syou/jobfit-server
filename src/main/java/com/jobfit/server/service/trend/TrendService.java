package com.jobfit.server.service.trend;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.trend.TrendRepository;
import com.jobfit.server.domain.trend.TrendType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrendService {

	private final TrendRepository trendRepository;

	@Transactional(readOnly = true)
	public TrendInfos getTrends(GetTrendsCommand command) {

		List<TrendInfo> growth = trendRepository.searchTrends(TrendType.GROWTH, command.getSearchKey(),
			command.getPageable()).map(TrendInfo::from).getContent();

		List<TrendInfo> stable = trendRepository.searchTrends(TrendType.STABLE, command.getSearchKey(),
			command.getPageable()).map(TrendInfo::from).getContent();

		List<TrendInfo> decline = trendRepository.searchTrends(TrendType.DECLINE, command.getSearchKey(),
			command.getPageable()).map(TrendInfo::from).getContent();

		return new TrendInfos(growth, stable, decline);
	}
}
