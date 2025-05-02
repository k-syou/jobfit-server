package com.jobfit.server.infras.otp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfit.server.domain.otp.Otp;
import com.jobfit.server.domain.otp.OtpType;

public interface OtpJpaRepository extends JpaRepository<Otp, Long> {
	Optional<Otp> findByOtp(String otp);
	Optional<Otp> findByEmailAndOtp(String email, String otp);
	Optional<Otp> findByEmail(String email);

	Optional<Otp> findByEmailAndTypeAndOtp(String email, OtpType type, String otp);
}
