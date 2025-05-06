package com.jobfit.server.domain.skill;

import java.util.List;
import java.util.Optional;

public interface UserSkillRepository {

	UserSkill save(UserSkill userSkill);
	Optional<UserSkill> findById(Long id);
	Optional<UserSkill> findByUserId(Long userId);
	List<Certification> findAllByNameLikeOrAll(String name);
}
