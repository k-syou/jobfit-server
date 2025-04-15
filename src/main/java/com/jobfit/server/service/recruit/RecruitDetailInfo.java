package com.jobfit.server.service.recruit;

import java.time.LocalDate;

import com.jobfit.server.domain.recruit.Recruit;

import lombok.Builder;

public class RecruitDetailInfo {
  private Long recruitId;
  private Long jcId;
  private String category;
  private String companyName;
  private String summary;
  private String recruitNumber;
  private String educationType;
  private String jobType;
  private String workPlace;
  private String content;
  private String careerType;
  private String wage;
  private String workTime;
  private String workType;
  private String workSchedule;
  private String totalTime;
  private String insurance;
  private String recruitmentMethod;
  private String applyMethod;
  private String applyDocument;
  private String manager;
  private String managerPhonenumber;
  private String managerOrganization;
  private String companyAddress;
  private String title;
  private String jobPosting;  
  private LocalDate registerDate;
  private LocalDate endDate;
  
  @Builder
  public RecruitDetailInfo(
    Long recruitId,
    Long jcId,
    String category,
    String companyName,
    String summary,
    String recruitNumber,
    String educationType,
    String jobType,
    String workPlace,
    String content,
    String careerType,
    String wage,
    String workTime,
    String workType,
    String workSchedule,
    String totalTime,
    String insurance,
    String recruitmentMethod,
    String applyMethod,
    String applyDocument,
    String manager,
    String managerPhonenumber,
    String managerOrganization,
    String companyAddress,
    String title,
    String jobPosting,
    LocalDate registerDate,
    LocalDate endDate
  ) {
    this.recruitId = recruitId;
    this.jcId = jcId;
    this.category = category;
    this.companyName = companyName;
    this.summary = summary;
    this.recruitNumber = recruitNumber;
    this.educationType = educationType;
    this.jobType = jobType;
    this.workPlace = workPlace;
    this.content = content;
    this.careerType = careerType;
    this.wage = wage;
    this.workTime = workTime;
    this.workType = workType;
    this.workSchedule = workSchedule;
    this.totalTime = totalTime;
    this.insurance = insurance;
    this.recruitmentMethod = recruitmentMethod;
    this.applyMethod = applyMethod;
    this.applyDocument = applyDocument;
    this.manager = manager;
    this.managerPhonenumber = managerPhonenumber;
    this.managerOrganization = managerOrganization;
    this.companyAddress = companyAddress;
    this.title = title;
    this.jobPosting = jobPosting;
    this.registerDate = registerDate;
    this.endDate = endDate;
  }

}
