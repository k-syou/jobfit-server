package com.jobfit.server.domain.favorite;

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
public class Favorite extends BaseEntity {

	@Id
	@Column(name = "favorite_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private Long recruitId;

	public Favorite(Long userId, Long recruitId) {
		this.userId = userId;
		this.recruitId = recruitId;
	}
}
