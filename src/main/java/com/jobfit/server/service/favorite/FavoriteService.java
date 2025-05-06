package com.jobfit.server.service.favorite;

import static com.jobfit.server.support.exception.BusinessError.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.favorite.Favorite;
import com.jobfit.server.domain.favorite.FavoriteRepository;
import com.jobfit.server.support.exception.BusinessError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {

	private final FavoriteRepository favoriteRepository;

	@Transactional(readOnly = true)
	public List<FavoriteInfo> getFavorites(GetFavoriteCommand command) {
		return favoriteRepository.findAllByUserId(command.getUserId())
			.stream()
			.map(FavoriteInfo::from)
			.toList();
	}

	@Transactional
	public void favorite(FavoriteCommand command) {
		favoriteRepository.findByUserIdAndRecruitId(command.getUserId(), command.getRecruitId())
			.ifPresent(favorite -> {
				throw ALREADY_FAVORITE_RECRUIT.exception();
			});

		Favorite favorite = new Favorite(command.getUserId(), command.getRecruitId());

		favoriteRepository.save(favorite);
	}

	@Transactional
	public void deleteFavorite(FavoriteCommand command) {
		Favorite favorite = favoriteRepository.findByUserIdAndRecruitId(command.getUserId(), command.getRecruitId())
			.orElseThrow(NOT_FOUND_FAVORITE_ERROR::exception);

		favoriteRepository.delete(favorite);
	}
}
