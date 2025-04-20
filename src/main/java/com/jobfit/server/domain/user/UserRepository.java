package com.jobfit.server.domain.user;

import java.util.Optional;

public interface UserRepository {
	User save(User user);
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
