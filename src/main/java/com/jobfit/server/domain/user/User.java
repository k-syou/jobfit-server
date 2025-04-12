package com.jobfit.server.domain.user;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.jobfit.server.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String username;
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
