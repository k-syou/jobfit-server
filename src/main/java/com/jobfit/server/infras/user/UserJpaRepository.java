package com.jobfit.server.infras.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfit.server.domain.user.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
