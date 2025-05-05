package com.jobfit.server.domain.skill;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@Component
@RequiredArgsConstructor
public class SkillsConverter implements AttributeConverter<Skills, String> {

	private final ObjectMapper objectMapper;

	@Override
	public String convertToDatabaseColumn(Skills skills) {
		try {
			return objectMapper.writeValueAsString(skills);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Skills JSON 변환 실패", e);
		}
	}

	@Override
	public Skills convertToEntityAttribute(String json) {
		try {
			return objectMapper.readValue(json, Skills.class);
		} catch (IOException e) {
			throw new IllegalArgumentException("Skills JSON 파싱 실패", e);
		}
	}
}
