package com.jobfit.server.domain.trend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrendRepository {
	Page<Trend> searchTrends(TrendType type, String searchKey, Pageable pageable);
}

