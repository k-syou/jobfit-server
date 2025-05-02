package com.jobfit.server.infras.user;

import org.springframework.stereotype.Repository;

import com.jobfit.server.domain.user.User;
import com.jobfit.server.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public User save(User user) {
		return userJpaRepository.save(user);
	}
	@Override
	public Optional<User> findByEmail(String email) {
		return userJpaRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByUsernameAndEmail(String username, String email) {
		return userJpaRepository.findByUsernameAndEmail(username, email);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userJpaRepository.findByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userJpaRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userJpaRepository.existsByUsername(username);
	}

	@Override
	public Optional<User> findById(Long userId) {
		return userJpaRepository.findById(userId);
	}

}
