package com.jobfit.server.interfaces.api.skill;

import java.util.List;
import java.util.Map;

import com.jobfit.server.service.skill.PatchSkillCommand;

import lombok.Getter;

@Getter
public class PatchSkillRequest {

	private String career;
	private List<String> certificates;
	private List<Map<String, String>> specs;

	public PatchSkillRequest(String career, List<String> certificates, List<Map<String, String>> specs) {
		this.career = career;
		this.certificates = certificates;
		this.specs = specs;
	}

	public PatchSkillCommand toCommand(Long userId) {
		return new PatchSkillCommand(userId, career, certificates, specs);
	}
}
