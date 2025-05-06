package com.jobfit.server.service.skill;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.skill.Certification;
import com.jobfit.server.domain.skill.Skills;
import com.jobfit.server.domain.skill.UserSkill;
import com.jobfit.server.domain.skill.UserSkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {

	private final UserSkillRepository userSkillRepository;

	@Transactional
	public SkillInfo getSkill(Long userId) {
		return userSkillRepository.findByUserId(userId)
			.map(SkillInfo::from)
			.orElseGet(() -> {
				UserSkill newUserSkill = new UserSkill(userId, new Skills("", List.of(), List.of()));
				UserSkill saved = userSkillRepository.save(newUserSkill);
				return SkillInfo.from(saved);
			});
	}

	@Transactional
	public SkillInfo patchSkill(PatchSkillCommand command) {
		UserSkill userSkill = userSkillRepository.findByUserId(command.getUserId()).orElseThrow();
		userSkill.patchSkills(new Skills(command.getCareer(), command.getCertificates(), command.getSpecs()));
		userSkillRepository.save(userSkill);
		return SkillInfo.from(userSkill);
	}

	@Transactional(readOnly = true)
	public List<CertificationInfo> getCertifications(String name) {
		return userSkillRepository.findAllByNameLikeOrAll(name)
			.stream()
			.map(CertificationInfo::from)
			.toList();
	}
}
