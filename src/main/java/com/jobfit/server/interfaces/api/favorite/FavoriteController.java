package com.jobfit.server.interfaces.api.favorite;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.service.favorite.FavoriteInfo;
import com.jobfit.server.service.favorite.FavoriteService;
import com.jobfit.server.service.favorite.GetFavoriteCommand;
import com.jobfit.server.support.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

	private final FavoriteService favoriteService;

	@GetMapping("/api/v1/favorites")
	public ResponseEntity<ApiResponse<List<FavoriteInfo>>> getFavorites(@AuthenticationPrincipal CustomUserDetails user) {
		return ApiResponse.OK(favoriteService.getFavorites(new GetFavoriteCommand(user.getUserId())));
	}

	@PostMapping("/api/v1/favorites")
	public ResponseEntity<ApiResponse<Void>> favorites(
		@AuthenticationPrincipal CustomUserDetails user,
		@RequestBody FavoriteRequest request
	) {
		favoriteService.favorite(request.toCommand(user.getUserId()));
		return ApiResponse.OK(null);
	}

	@DeleteMapping("/api/v1/favorites")
	public ResponseEntity<ApiResponse<Void>> deleteFavorites(
		@AuthenticationPrincipal CustomUserDetails user,
		@RequestBody FavoriteRequest request
	) {
		favoriteService.deleteFavorite(request.toCommand(user.getUserId()));
		return ApiResponse.OK(null);
	}

}
