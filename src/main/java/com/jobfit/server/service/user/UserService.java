package com.jobfit.server.service.user;

import static com.jobfit.server.support.exception.BusinessError.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.otp.Otp;
import com.jobfit.server.domain.otp.OtpRepository;
import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final OtpRepository otpRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserInfo signUp(UserSignUpCommand command) {

		userRepository.findByUsername(command.getUsername())
			.ifPresent(user -> { throw USER_USERNAME_DUPLICATE_ERROR.exception(); });

		userRepository.findByEmail(command.getEmail())
			.ifPresent(user -> { throw USER_EMAIL_DUPLICATE_ERROR.exception(); });

		Otp otp = otpRepository.findByEmailAndOtp(command.getEmail(), command.getOtp())
			.orElseThrow(NOT_FOUND_OTP_ERROR::exception);

		User user = User.create(command.getEmail(), command.getUsername(), passwordEncoder.encode(command.getPassword()), command.getName());

		user.signUp(otp);

		userRepository.save(user);
		return UserInfo.from(user);
	}

	@Transactional
	public boolean isEmailDuplicated(UserCheckDuplicatedEmailCommand command) {
		return userRepository.existsByEmail(command.getEmail());
	}

	@Transactional
	public void withDrawUser(UserWithDrawCommand command){
		User user = userRepository.findById(command.getUserId())
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);

		user.withDraw();
	}

	@Transactional(readOnly = true)
	public UserInfo getProfile(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);
		return UserInfo.from(user);
	}
}
