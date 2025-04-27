package com.jobfit.server.support.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobfit.server.interfaces.api.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		setResponse(response);
	}

	private void setResponse(HttpServletResponse response) throws IOException {

		log.error("[exceptionHandle] AuthenticationEntryPoint = {}", "AccessToken을 확인해주세요");

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ApiResponse<Void> apiResponse = new ApiResponse<>(
			HttpStatus.UNAUTHORIZED,
			HttpStatus.UNAUTHORIZED.value(),
			"AccessToken을 확인해주세요",
			null
		);

		String body = objectMapper.writeValueAsString(apiResponse);
		response.getWriter().write(body);
	}
}
