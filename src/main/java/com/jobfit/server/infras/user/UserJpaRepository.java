package com.jobfit.server.infras.user;

import com.jobfit.server.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

	Optional<User> findByUsernameAndEmail(String username, String email);
}
