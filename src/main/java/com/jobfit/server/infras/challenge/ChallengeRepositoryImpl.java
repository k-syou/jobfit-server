package com.jobfit.server.infras.challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.jobfit.server.domain.challenge.Challenge;
import com.jobfit.server.domain.challenge.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChallengeRepositoryImpl implements ChallengeRepository {

	private final ChallengeJpaRepository challengeJpaRepository;

	@Override
	public Optional<Challenge> findById(Long id) {
		return challengeJpaRepository.findById(id);
	}

	@Override
	public Challenge save(Challenge challenge) {
		return challengeJpaRepository.save(challenge);
	}

	@Override
	public List<ChallengeListDto> findAllByUserId(Long userId) {
		return challengeJpaRepository.findAllByUserIdWithRecruitId(userId);
	}

	@Override
	public Optional<Challenge> findByUserIdAndRecruitId(Long userId, Long recruitId) {
		return challengeJpaRepository.findByUserIdAndRecruitId(userId, recruitId);
	}

	@Override
	public void delete(Challenge challenge) {
		challengeJpaRepository.delete(challenge);
	}

	@Override
	public Optional<ChallengeDetailDto> findByIdWithRecruit(Long challengeId) {
		return challengeJpaRepository.findByIdWithRecruit(challengeId);
	}
}
