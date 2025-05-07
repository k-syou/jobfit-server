package com.jobfit.server.infras.recruit;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

// import com.jobfit.server.domain.recruit.Category;
import com.jobfit.server.domain.recruit.Recruit;
import com.jobfit.server.domain.recruit.RecruitRepository;

// import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RecruitRepositoryImpl implements RecruitRepository {

  private final RecruitJpaRepository recruitJpaRepository;
  // private final EntityManager em;

  @Override
  public Recruit save(Recruit recruit) {
    return recruitJpaRepository.save(recruit);
  }

  @Override
  public void delete(Recruit recruit) {
    recruitJpaRepository.delete(recruit);
  }

  @Override
  public List<Recruit> findAll() {
    return recruitJpaRepository.findAll();
  }

  @Override
  public Optional<Recruit> findById(Long recruitId) {
    return recruitJpaRepository.findById(recruitId);
  }

  @Override
  public Page<RecruitWithFavoriteDto> findRecruitsWithFavoriteStatus(Long userId, String companyName, String region, String job,
      String careerType, Pageable pageable) {
    return recruitJpaRepository.findRecruitsWithFavoriteStatus(userId, companyName, region, job, careerType, pageable);
  }
}
