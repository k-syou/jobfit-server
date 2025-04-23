package com.jobfit.server.domain.recruit;

import java.util.List;
import java.util.Optional;

public interface RecruitRepository {
    Recruit save(Recruit recruit);

    void delete(Recruit recruit);

    List<Recruit> findAll();

    Optional<Recruit> findById(Long recruitId);
}
