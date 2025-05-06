package com.jobfit.server.service.favorite;

import java.time.LocalDate;

import com.jobfit.server.infras.favorite.FavoriteRecruitDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FavoriteInfo {

	private Long userId;
	private Long recruitId;
	private String title;
	private String companyName;
	private String wage;
	private String workPlace;
	private String jobType;
	private LocalDate registerDate;
	private LocalDate endDate;

	@Builder
	private FavoriteInfo(Long userId, Long recruitId, String title, String companyName, String wage, String workPlace, String jobType, LocalDate registerDate, LocalDate endDate) {
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

	public static FavoriteInfo from(FavoriteRecruitDto dto) {
		return FavoriteInfo.builder()
			.userId(dto.getUserId())
			.recruitId(dto.getRecruitId())
			.title(dto.getTitle())
			.companyName(dto.getCompanyName())
			.wage(dto.getWage())
			.workPlace(dto.getWorkPlace())
			.jobType(dto.getJobType())
			.registerDate(dto.getRegisterDate())
			.endDate(dto.getEndDate())
			.build();
	}
}
