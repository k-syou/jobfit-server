package com.jobfit.server.service.user;

import static com.jobfit.server.support.exception.BusinessError.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;
import com.jobfit.server.domain.user.UserStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserInfo signUp(UserSignUpCommand command) {

		userRepository.findByEmail(command.getEmail())
			.ifPresent(user -> { throw USER_EMAIL_DUPLICATE_ERROR.exception(); });

		User user = new User(command.getEmail(), command.getUsername(), passwordEncoder.encode(command.getPassword()),command.getNickname(), UserStatus.ACTIVE);

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

	public UserInfo getProfile(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(USER_NOT_FOUND_ERROR::exception);
		return UserInfo.from(user);
	}
}
