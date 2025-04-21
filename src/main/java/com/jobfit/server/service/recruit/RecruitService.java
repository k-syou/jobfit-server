package com.jobfit.server.service.recruit;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jobfit.server.domain.recruit.Recruit;
import com.jobfit.server.domain.recruit.RecruitRepository;
import com.jobfit.server.support.exception.BusinessError;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitService {

	private final RecruitRepository recruitRepository;

	@Transactional
	public List<RecruitInfo> getRecentRecruits() {
		return recruitRepository.findAll().stream().sorted(
			Comparator.comparing(Recruit::getRegisterDate))
			.map(RecruitInfo::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public List<RecruitInfo> getEndRecruits() {
		return recruitRepository.findAll().stream().sorted(
			Comparator.comparing(Recruit::getEndDate, Comparator.reverseOrder()))
			.map(RecruitInfo::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public RecruitDetailInfo getRecruit(Long recruitId) {
		return RecruitDetailInfo.from(recruitRepository.findById(recruitId).orElseThrow(
			BusinessError.NOT_FOUND_RECRUIT_ERROR::exception));
	}
}
