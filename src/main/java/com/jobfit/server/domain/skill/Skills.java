package com.jobfit.server.domain.skill;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class Skills {
	private String career;
	private List<String> certificates;
	private List<Map<String, String>> specs;

	public Skills(String career, List<String> certificates, List<Map<String, String>> specs) {
		this.career = career;
		this.certificates = certificates;
		this.specs = specs;
	}
}
