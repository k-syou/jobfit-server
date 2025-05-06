package com.jobfit.server.infras.favorite;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jobfit.server.domain.favorite.Favorite;
import com.jobfit.server.domain.favorite.FavoriteRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepository {

	private final FavoriteJpaRepository favoriteJpaRepository;

	@Override
	public Favorite save(Favorite favorite) {
		return favoriteJpaRepository.save(favorite);
	}

	@Override
	public void delete(Favorite favorite) {
		favoriteJpaRepository.delete(favorite);
	}

	@Override
	public List<Favorite> findAll() {
		return favoriteJpaRepository.findAll();
	}

	@Override
	public Optional<Favorite> findById(Long id) {
		return favoriteJpaRepository.findById(id);
	}

	@Override
	public Optional<Favorite> findByUserId(Long userId) {
		return favoriteJpaRepository.findByUserId(userId);
	}

	@Override
	public List<FavoriteRecruitDto> findAllByUserId(Long userId) {
		return favoriteJpaRepository.findFavoritesWithRecruitByUserId(userId);
	}

	@Override
	public Optional<Favorite> findByUserIdAndRecruitId(Long userId, Long recruitId) {
		return favoriteJpaRepository.findByUserIdAndRecruitId(userId, recruitId);
	}
}
