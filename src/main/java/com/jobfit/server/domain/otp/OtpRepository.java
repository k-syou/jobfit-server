package com.jobfit.server.domain.otp;

import java.util.Optional;

public interface OtpRepository {
	Otp save(Otp otp);
	Optional<Otp> findByOtp(String otp);
	Optional<Otp> findByEmailAndOtp(String email, String otp);

	Optional<Otp> findByEmail(String email);
	Optional<Otp> findById(Long id);
}
