package com.jobfit.server.domain.recruit;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobfit.server.infras.recruit.RecruitWithFavoriteDto;

public interface RecruitRepository {
    Recruit save(Recruit recruit);

    void delete(Recruit recruit);

    List<Recruit> findAll();

    Optional<Recruit> findById(Long recruitId);

    Page<RecruitWithFavoriteDto> findRecruitsWithFavoriteStatus(
        Long userId,
        String companyName,
        String region,
        String job,
        String careerType,
        Pageable pageable
    );
}
