package com.jobfit.server.domain.recruit;

import java.util.List;

public interface RecruitRepository{
    Recruit save(Recruit recruit);
    void delete(Recruit recruit);
    List<Recruit> findAll();
}
