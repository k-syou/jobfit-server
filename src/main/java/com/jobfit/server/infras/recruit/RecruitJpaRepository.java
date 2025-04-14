package com.jobfit.server.infras.recruit;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfit.server.domain.recruit.Recruit;

public interface RecruitJpaRepository extends JpaRepository<Recruit, Long> {
}
