package com.jobfit.server.infras.favorite;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class FavoriteRecruitDto {
	private Long userId;
	private Long recruitId;
	private String title;
	private String companyName;
	private String wage;
	private String workPlace;
	private String jobType;
	private LocalDate registerDate;
	private LocalDate endDate;

	public FavoriteRecruitDto(Long userId, Long recruitId, String title, String companyName, String wage,
		String workPlace,
		String jobType, LocalDate registerDate, LocalDate endDate) {
		this.userId = userId;
		this.recruitId = recruitId;
		this.title = title;
		this.companyName = companyName;
		this.wage = wage;
		this.workPlace = workPlace;
		this.jobType = jobType;
		this.registerDate = registerDate;
		this.endDate = endDate;
	}
}
