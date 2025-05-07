package com.jobfit.server.domain.challenge;

import java.util.List;
import java.util.Optional;

import com.jobfit.server.infras.challenge.ChallengeDetailDto;
import com.jobfit.server.infras.challenge.ChallengeListDto;

public interface ChallengeRepository {
	Optional<Challenge> findById(Long id);
	Challenge save(Challenge challenge);
	List<ChallengeListDto> findAllByUserId(Long userId);
	Optional<Challenge> findByUserIdAndRecruitId(Long userId, Long recruitId);
	void delete(Challenge challenge);

	Optional<ChallengeDetailDto> findByIdWithRecruit(Long challengeId);
}
