package com.jobfit.server.service.challenge;

import static com.jobfit.server.support.exception.BusinessError.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.challenge.Challenge;
import com.jobfit.server.domain.challenge.ChallengeRepository;
import com.jobfit.server.domain.challenge.ChallengeStatus;
import com.jobfit.server.infras.challenge.ChallengeDetailDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {

	private final ChallengeRepository challengeRepository;

	@Transactional
	public ChallengeInfo createChallenge(CreateChallengeCommand command) {

		challengeRepository.findByUserIdAndRecruitId(command.getUserId(), command.getRecruitId())
			.ifPresent(challenge -> {
				throw ALREADY_CHALLENGE_RECRUIT.exception();
			});

		Challenge challenge = new Challenge(command.getUserId(), command.getRecruitId(), 0L, null, null, ChallengeStatus.PENDING);

		challengeRepository.save(challenge);
		return ChallengeInfo.from(challenge);
	}

	@Transactional
	public ChallengeInfo deleteChallenge(Long challengeId) {
		Challenge challenge = challengeRepository.findById(challengeId)
			.orElseThrow(NOT_FOUND_CHALLENGE_ERROR::exception);
		challengeRepository.delete(challenge);
		return ChallengeInfo.from(challenge);
	}

	@Transactional
	public List<ChallengeListInfo> getChallenges(Long userId) {
		return challengeRepository.findAllByUserId(userId).stream()
			.map(ChallengeListInfo::from)
			.toList();
	}

	@Transactional
	public ChallengeDetailInfo getChallenge(Long challengeId) {
		ChallengeDetailDto dto = challengeRepository.findByIdWithRecruit(challengeId)
			.orElseThrow();
		return ChallengeDetailInfo.from(dto);
	}
}
