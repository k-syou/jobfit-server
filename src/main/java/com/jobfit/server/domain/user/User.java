package com.jobfit.server.domain.user;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.jobfit.server.domain.BaseEntity;
import com.jobfit.server.domain.otp.Otp;

import jakarta.persistence.*;
import lombok.Builder;
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
	private UserStatus status;

	@Builder
	private User(String email, String username, String password, String nickname) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.status = UserStatus.ACTIVE;
	}

	public User(Long id, String email,String username, String password,String nickname,UserStatus status) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.status = status != null? status : UserStatus.ACTIVE;
		this.createdAt = LocalDateTime.now();
	}

	public static User create(String email, String username, String password, String nickname) {
		return User.builder()
			.email(email)
			.username(username)
			.password(password)
			.nickname(nickname)
			.build();
	}

	public void signUp(Otp otp) {
		otp.validateVerified();
	}

	public void withDraw(){
		this.status = UserStatus.WITHDRAW;
	}
}
