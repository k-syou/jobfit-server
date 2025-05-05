package com.jobfit.server.domain.skill;

import com.jobfit.server.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSkill extends BaseEntity {

	@Id
	@Column(name = "user_skill_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	@Column(columnDefinition = "json")
	@Convert(converter = SkillsConverter.class)
	private Skills skills;

	public UserSkill(Long userId, Skills skills) {
		this.userId = userId;
		this.skills = skills;
	}

	public void patchSkills(Skills skills) {
		this.skills = skills;
	}
}
