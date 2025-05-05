package com.jobfit.server.service.skill;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class PatchSkillCommand {

	private Long userId;
	private String career;
	private List<String> certificates;
	private List<Map<String, String>> specs;

	public PatchSkillCommand(Long userId, String career, List<String> certificates, List<Map<String, String>> specs) {
		this.userId = userId;
		this.career = career;
		this.certificates = certificates;
		this.specs = specs;
	}
}
