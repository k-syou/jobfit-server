package com.jobfit.server.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public UserInfo signUp(UserSignUpCommand command) {
		User user = new User(command.getUsername(), command.getPassword());
		userRepository.save(user);
		return UserInfo.from(user);
	}

}
