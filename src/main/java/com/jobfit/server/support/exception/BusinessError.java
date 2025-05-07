package com.jobfit.server.support.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum BusinessError {

	// 유저 관련 Error
	USER_SIGNUP_PASSWORD_NOT_MATCH(BAD_REQUEST, "password와 confirmPassword가 다릅니다."),
	USER_SIGNUP_EMAIL_NULL_OR_EMPTY(BAD_REQUEST, "이메일을 입력해주세요."),
	USER_SIGNUP_USERNAME_NULL_OR_EMPTY(BAD_REQUEST, "아이디를 입력해주세요."),
	USER_SIGNUP_PASSWORD_NULL_OR_EMPTY(BAD_REQUEST, "비밀번호를 입력해주세요."),
	USER_SIGNUP_CONFIRM_PASSWORD_NULL_OR_EMPTY(BAD_REQUEST, "비밀번호 확인를 입력해주세요."),
	USER_SIGNUP_NAME_NULL_OR_EMPTY(BAD_REQUEST, "이름을 입력해주세요."),
	USER_SIGNUP_OTP_NULL_OR_EMPTY(BAD_REQUEST, "OTP 번호를 입력해주세요."),
	USER_SIGNUP_USERNAME_DUPLICATE_NULL_OR_EMPTY(BAD_REQUEST, "아이디를 입력해주세요."),
	USER_SIGNUP_OTP_NOT_FOUND(NOT_FOUND, "인증정보를 확인할 수 없습니다."),
	NOT_VERIFIED_OTP_ERROR(BAD_REQUEST, "이메일 인증을 진행해주세요."),
	USER_NOT_FOUND_ERROR(NOT_FOUND, "회원을 찾을 수 없습니다."),
	USER_EMAIL_DUPLICATE_ERROR(BAD_REQUEST, "이미 가입된 이메일입니다."),
	USER_USERNAME_DUPLICATE_ERROR(BAD_REQUEST, "이미 가입된 아이디입니다."),
	USER_EDIT_NEW_PASSWORD_NULL_OR_EMPTY(BAD_REQUEST, "변경하실 비밀번호를 입력해주세요."),
	USER_EDIT_CONFIRM_PASSWORD_NULL_OR_EMPTY(BAD_REQUEST, "새로운 비밀번호와 일치하지 않습니다."),

	// 메일 관련 Error
	MAIL_SEND_EMAIL_NULL_OR_EMPTY(BAD_REQUEST, "이메일을 입력해주세요."),
	MAIL_SEND_TYPE_NULL_OR_EMPTY(BAD_REQUEST, "타입을 입력해주세요."),
	CHECK_OTP_NULL_OR_EMPTY(BAD_REQUEST, "otp번호를 입력해주세요."),
	NOT_FOUND_OTP_ERROR(NOT_FOUND, "인정정보를 찾을 수 없습니다."),
	EXPIRED_OTP_ERROR(BAD_REQUEST, "인증시간이 만료되었습니다."),

	// 채용공고 관련 Error
	NOT_FOUND_RECRUIT_ERROR(NOT_FOUND, "해당 채용공고를 찾을 수 없습니다."),

	// 즐겨찾기 관련 Error
	ALREADY_FAVORITE_RECRUIT(BAD_REQUEST, "이미 즐겨찾기한 채용공고입니다."),
	NOT_FOUND_FAVORITE_ERROR(NOT_FOUND, "좋아요 기록이 없습니다."),

	// 챌린지 관련 Error
	ALREADY_CHALLENGE_RECRUIT(BAD_REQUEST, "이미 챌리지한 채용공고입니다."),
	NOT_FOUND_CHALLENGE_ERROR(NOT_FOUND, "삭제할 데이터가 없습니다.");

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
