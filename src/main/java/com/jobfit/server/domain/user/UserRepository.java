package com.jobfit.server.domain.user;

import java.util.Optional;

public interface UserRepository {
	User save(User user);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	Optional<User> findById(Long userId);
	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
}
