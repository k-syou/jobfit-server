package com.jobfit.server.infras.favorite;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobfit.server.domain.favorite.Favorite;

public interface FavoriteJpaRepository extends JpaRepository<Favorite, Long> {
	Optional<Favorite> findByUserId(Long userId);
	List<Favorite> findAllByUserId(Long userId);

	@Query("""
		SELECT new com.jobfit.server.infras.favorite.FavoriteRecruitDto(
		f.userId, f.recruitId, r.title, r.companyName, r.wage, r.workPlace, r.jobType, r.careerType, r.registerDate, r.endDate
		)
		FROM Favorite f
		JOIN Recruit r ON f.recruitId = r.id
		WHERE f.userId = :userId
	""")
	List<FavoriteRecruitDto> findFavoritesWithRecruitByUserId(@Param("userId") Long userId);

	Optional<Favorite> findByUserIdAndRecruitId(Long userId, Long recruitId);
}
