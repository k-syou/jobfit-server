package com.jobfit.server.infras.otp;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jobfit.server.domain.otp.Otp;
import com.jobfit.server.domain.otp.OtpRepository;
import com.jobfit.server.domain.otp.OtpType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OtpRepositoryImpl implements OtpRepository {

	private final OtpJpaRepository otpJpaRepository;

	@Override
	public Otp save(Otp otp) {
		return otpJpaRepository.save(otp);
	}

	@Override
	public Optional<Otp> findByOtp(String otp) {
		return otpJpaRepository.findByOtp(otp);
	}

	@Override
	public Optional<Otp> findByEmailAndOtp(String email, String otp) {
		return otpJpaRepository.findByEmailAndOtp(email, otp);
	}

	@Override
	public Optional<Otp> findByEmail(String email) {
		return otpJpaRepository.findByEmail(email);
	}

	@Override
	public Optional<Otp> findById(Long id) {
		return otpJpaRepository.findById(id);
	}

	@Override
	public Optional<Otp> findByEmailAndTypeAndOtp(String email, OtpType type, String otp) {
		return otpJpaRepository.findByEmailAndTypeAndOtp(email, type, otp);
	}
}
