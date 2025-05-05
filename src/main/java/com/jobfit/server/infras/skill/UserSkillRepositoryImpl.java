package com.jobfit.server.infras.skill;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jobfit.server.domain.skill.UserSkill;
import com.jobfit.server.domain.skill.UserSkillRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserSkillRepositoryImpl implements UserSkillRepository {

	private final UserSkillJpaRepository userSkillJpaRepository;

	@Override
	public UserSkill save(UserSkill userSkill) {
		return userSkillJpaRepository.save(userSkill);
	}

	@Override
	public Optional<UserSkill> findById(Long id) {
		return userSkillJpaRepository.findById(id);
	}

	@Override
	public Optional<UserSkill> findByUserId(Long userId) {
		return userSkillJpaRepository.findByUserId(userId);
	}
}
