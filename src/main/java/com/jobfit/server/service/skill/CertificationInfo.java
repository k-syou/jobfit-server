package com.jobfit.server.service.skill;

import com.jobfit.server.domain.skill.Certification;

import lombok.Getter;

@Getter
public class CertificationInfo {

	private String name;

	public CertificationInfo(String name) {
		this.name = name;
	}

	public static CertificationInfo from(Certification certification) {
		return new CertificationInfo(certification.getName());
	}
}
