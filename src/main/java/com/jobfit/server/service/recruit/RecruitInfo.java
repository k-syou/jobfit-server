package com.jobfit.server.service.recruit;

import com.jobfit.server.domain.recruit.Recruit;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RecruitInfo {

  private Long recruitId;
  private String title; // 공고 이름
  private String companyName; // 회사 이름
  private String wage; // 연봉
  private String workType; // 근무 형태
  private String workPlace; // 근무예정지
  private String careerType; // 경력
  private LocalDate registerDate; // 등록일
  private LocalDate endDate; // 마감일

  @Builder
  private RecruitInfo(
      Long recruitId,
      String title,
      String companyName,
      String wage,
      String workType,
      String workPlace,
      String careerType,
      LocalDate registerDate,
      LocalDate endDate) {
    this.recruitId = recruitId;
    this.title = title;
    this.companyName = companyName;
    this.wage = wage;
    this.workType = workType;
    this.workPlace = workPlace;
    this.careerType = careerType;
    this.registerDate = registerDate;
    this.endDate = endDate;
  }

  public static RecruitInfo from(Recruit recruit) {
    return RecruitInfo.builder()
        .recruitId(recruit.getId())
        .title(recruit.getTitle())
        .companyName(recruit.getCompanyName())
        .wage(recruit.getWage())
        .workType(recruit.getWorkType())
        .workPlace(recruit.getWorkPlace())
        .careerType(recruit.getCareerType())
        .registerDate(recruit.getRegisterDate())
        .endDate(recruit.getEndDate())
        .build();
  }
}
