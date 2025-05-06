package com.jobfit.server.domain.skill;

import com.jobfit.server.domain.BaseEntity;

import jakarta.persistence.Column;
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
public class Certification extends BaseEntity {

	@Id
	@Column(name = "certification_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
}
