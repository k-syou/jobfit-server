package com.jobfit.server.support.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum BusinessError {

	// 유저 관련 Error
	USER_SIGNUP_PASSWORD_NOT_MATCH(BAD_REQUEST, "password와 confirmPassword가 다릅니다."),
	USER_SIGNUP_EMAIL_NULL_OR_EMPTY(BAD_REQUEST, "이메일을 입력해주세요."),
	USER_SIGNUP_USERNAME_NULL_OR_EMPTY(BAD_REQUEST, "이름을 입력해주세요."),
	USER_SIGNUP_PASSWORD_NULL_OR_EMPTY(BAD_REQUEST, "비밀번호를 입력해주세요."),
	USER_SIGNUP_CONFIRM_PASSWORD_NULL_OR_EMPTY(BAD_REQUEST, "비밀번호 확인를 입력해주세요."),
	USER_SIGNUP_NICKNAME_NULL_OR_EMPTY(BAD_REQUEST, "닉네임을 입력해주세요."),
	USER_NOT_FOUND_ERROR(NOT_FOUND, "회원을 찾을 수 없습니다."),
	USER_EMAIL_DUPLICATE_ERROR(BAD_REQUEST, "이미 가입된 이메일입니다."),

	// 채용공고 관련 Error
	NOT_FOUND_RECRUIT_ERROR(NOT_FOUND, "해당 채용공고를 찾을 수 없습니다.");


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
