package com.jobfit.server.domain.user;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.jobfit.server.domain.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String email;
	private String password;
	private String username;
	private String nickname;
	@Enumerated(EnumType.STRING)
	UserStatus status=UserStatus.ACTIVE;

	public User(String email,String username, String password,String nickname,UserStatus status) {
		this.email=email;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.status=status != null? status : UserStatus.ACTIVE;
		this.createdAt = LocalDateTime.now();
	}
}
