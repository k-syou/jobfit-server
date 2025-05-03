package com.jobfit.server.infras.trend;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jobfit.server.domain.trend.Trend;
import com.jobfit.server.domain.trend.TrendRepository;
import com.jobfit.server.domain.trend.TrendType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TrendRepositoryImpl implements TrendRepository {

	private final TrendJpaRepository trendJpaRepository;

	@Override
	public Page<Trend> searchTrends(TrendType type, String searchKey, Pageable pageable) {
		return trendJpaRepository.searchTrends(type, searchKey, pageable);
	}

}
