package com.jobfit.server.support.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum BusinessError {

	// 유저 관련 Error
	USER_SIGNUP_PASSWORD_NOT_MATCH(BAD_REQUEST, "password와 confirmPassword가 다릅니다.");


	private final HttpStatus httpStatus;
	private final String message;

	BusinessError(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public BusinessException exception() {
		return new BusinessException(httpStatus, message);
	}
}
