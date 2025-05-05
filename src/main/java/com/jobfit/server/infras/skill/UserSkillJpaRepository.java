package com.jobfit.server.infras.skill;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfit.server.domain.skill.UserSkill;

public interface UserSkillJpaRepository extends JpaRepository<UserSkill, Long> {
	Optional<UserSkill> findByUserId(Long userId);
}
