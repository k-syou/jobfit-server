package com.jobfit.server.domain.recruit;

import java.time.LocalDate;

import com.jobfit.server.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Recruit extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private Long jcId;

  @Column(name = "category")
  private String category;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "summary")
  private String summary;

  @Column(name = "recruit_number")
  private String recruitNumber;

  @Column(name = "education_type")
  private String educationType;

  @Column(name = "job_type")
  private String jobType;

  @Column(name = "work_place")
  private String workPlace;

  @Column(name = "content")
  private String content;

  @Column(name = "career_type")
  private String careerType;

  @Column(name = "wage")
  private String wage;

  @Column(name = "work_time")
  private String workTime;

  @Column(name = "work_type")
  private String workType;

  @Column(name = "work_schedule")
  private String workSchedule;

  @Column(name = "total_time")
  private String totalTime;

  @Column(name = "insurance")
  private String insurance;

  @Column(name = "recruitment_method")
  private String recruitmentMethod;

  private String applyMethod;

  @Column(name = "apply_document")
  private String applyDocument;

  @Column(name = "manager")
  private String manager;

  @Column(name = "manager_phonenumber")
  private String managerPhonenumber;

  @Column(name = "manager_organization")
  private String managerOrganization;

  @Column(name = "company_address")
  private String companyAddress;

  @Column(name = "title")
  private String title;

  @Column(name = "job_posting")
  private String jobPosting;  

  @Column(name = "register_date")
  private LocalDate registerDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  public Recruit(
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
