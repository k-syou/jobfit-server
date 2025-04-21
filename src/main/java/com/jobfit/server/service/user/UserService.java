package com.jobfit.server.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	@Transactional
	public UserInfo signUp(UserSignUpCommand command) {
		String encodedPassword = passwordEncoder.encode(command.getPassword());
		User user = new User(command.getEmail(), command.getUsername(), encodedPassword,command.getNickname(),command.getStatus() );
		userRepository.save(user);
		return UserInfo.from(user);
	}
	@Transactional
	public boolean isEmailDuplicated(UserCheckDuplicatedEmailCommand command) {
		return userRepository.existsByEmail(command.getEmail());
	}

	@Transactional
	public void withDrawUser(UserWithDrawCommand command){
		User user= userRepository.findByEmail(command.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자가 존재하지 않습니다"));

		user.withDraw();
	}
}
