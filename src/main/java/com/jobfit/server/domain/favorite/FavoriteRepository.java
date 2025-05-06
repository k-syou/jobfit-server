package com.jobfit.server.domain.favorite;

import java.util.List;
import java.util.Optional;

import com.jobfit.server.infras.favorite.FavoriteRecruitDto;

public interface FavoriteRepository {
	Favorite save(Favorite favorite);
	void delete(Favorite favorite);
	List<Favorite> findAll();
	Optional<Favorite> findById(Long id);
	Optional<Favorite> findByUserId(Long userId);
	List<FavoriteRecruitDto> findAllByUserId(Long userId);

	Optional<Favorite> findByUserIdAndRecruitId(Long userId, Long recruitId);
}
