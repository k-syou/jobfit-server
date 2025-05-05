package com.jobfit.server.service.skill;

import java.util.List;
import java.util.Map;

import com.jobfit.server.domain.skill.UserSkill;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SkillInfo {

	private Long userId;
	private String career;
	private List<String> certificates;
	private List<Map<String, String>> specs;

	@Builder
	private SkillInfo(Long userId, String career, List<String> certificates, List<Map<String, String>> specs) {
		this.userId = userId;
		this.career = career;
		this.certificates = certificates;
		this.specs = specs;
	}

	public static SkillInfo from(UserSkill userSkill) {
		return SkillInfo.builder()
			.userId(userSkill.getUserId())
			.career(userSkill.getSkills().getCareer())
			.certificates(userSkill.getSkills().getCertificates())
			.specs(userSkill.getSkills().getSpecs())
			.build();
	}
}
