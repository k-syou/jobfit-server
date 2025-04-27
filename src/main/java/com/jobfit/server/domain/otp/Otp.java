package com.jobfit.server.domain.otp;

import static com.jobfit.server.support.exception.BusinessError.*;

import java.time.LocalDateTime;

import com.jobfit.server.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Otp extends BaseEntity {

	@Id
	@Column(name = "otp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String otp;
	private Boolean isVerified;
	private LocalDateTime expiredAt;

	@Builder
	private Otp(String email, String otp) {
		this.email = email;
		this.otp = otp;
		this.isVerified = false;
		this.expiredAt = LocalDateTime.now().plusMinutes(5);
	}

	public static Otp create(String email, String otp) {
		return Otp.builder()
			.email(email)
			.otp(otp)
			.build();
	}

	public void verify() {
		if (LocalDateTime.now().isAfter(expiredAt)) {
			throw EXPIRED_OTP_ERROR.exception();
		}
		this.isVerified = true;
	}

	public void validateVerified() {
		if (!this.isVerified) {
			throw NOT_VERIFIED_OTP_ERROR.exception();
		}
	}
}
