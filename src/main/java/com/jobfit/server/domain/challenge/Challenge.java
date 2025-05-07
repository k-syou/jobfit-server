package com.jobfit.server.domain.challenge;

import com.jobfit.server.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseEntity {

	@Id
	@Column(name = "challenge_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private Long recruitId;
	private Long progress;

	private String strengths;
	private String weaknesses;

	@Enumerated(EnumType.STRING)
	private ChallengeStatus status;

	public Challenge(Long userId, Long recruitId, Long progress, String strengths, String weaknesses, ChallengeStatus status) {
		this.userId = userId;
		this.recruitId = recruitId;
		this.progress = progress;
		this.strengths = strengths;
		this.weaknesses = weaknesses;
		this.status = status;
	}
}
