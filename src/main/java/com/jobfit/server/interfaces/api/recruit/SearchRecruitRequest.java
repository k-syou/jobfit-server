package com.jobfit.server.interfaces.api.recruit;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jobfit.server.service.recruit.SearchRecruitCommand;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchRecruitRequest {

	private Integer page;
	private String sortType;
	private Integer size;
	private String companyName;
	private String region;
	private String job;
	private String careerType;

	public SearchRecruitRequest(Integer page, String sortType, Integer size, String companyName, String region, String job, String careerType) {
		this.page = page;
		this.sortType = sortType;
		this.size = size;
		this.companyName = companyName;
		this.region = region;
		this.job = job;
		this.careerType = careerType;
	}

	public SearchRecruitCommand toCommand(Long userId) {

		Sort sort;
		if ("마감일순".equals(sortType)) {
			sort = Sort.by(Sort.Direction.ASC, "endDate");
		} else {
			sort = Sort.by(Sort.Direction.DESC, "registerDate");
		}

		int pageNumber = (page != null && page >= 0) ? page : 0;
		int pageSize = (size != null && size > 0) ? size : 10;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		return new SearchRecruitCommand(userId, companyName, region, job, careerType, pageable);
	}
}
