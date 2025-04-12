package com.jobfit.server.infras.user;

import org.springframework.stereotype.Repository;

import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public User save(User user) {
		return userJpaRepository.save(user);
	}
}
