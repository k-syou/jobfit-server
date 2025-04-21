package com.jobfit.server.infras.recruit;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jobfit.server.domain.recruit.Recruit;
import com.jobfit.server.domain.recruit.RecruitRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RecruitRepositoryImpl implements RecruitRepository {

  private final RecruitJpaRepository recruitJpaRepository;

  @Override
  public Recruit save(Recruit recruit) {
    return recruitJpaRepository.save(recruit);
  }

  @Override
  public Optional<Recruit> findById(Long id) {
    return recruitJpaRepository.findById(id);
  }

  @Override
  public void delete(Recruit recruit) {
    recruitJpaRepository.delete(recruit);
  }

  @Override
  public List<Recruit> findAll() {
    return recruitJpaRepository.findAll();
  }
}
