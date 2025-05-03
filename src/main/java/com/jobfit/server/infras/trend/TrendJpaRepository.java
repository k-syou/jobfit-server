package com.jobfit.server.infras.trend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobfit.server.domain.trend.Trend;
import com.jobfit.server.domain.trend.TrendType;

public interface TrendJpaRepository extends JpaRepository<Trend, Long> {

	@Query("""
		SELECT t
		FROM Trend t
		WHERE (:type IS NULL OR t.type = :type)
		  AND (:searchKey IS NULL OR t.name LIKE %:searchKey% OR t.name LIKE %:searchKey%)
	""")
	Page<Trend> searchTrends(
		@Param("type") TrendType type,
		@Param("searchKey") String searchKey,
		Pageable pageable
	);
}
