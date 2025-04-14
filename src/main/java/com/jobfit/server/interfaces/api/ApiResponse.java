package com.jobfit.server.interfaces.api;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	public static final String DEFAULT_MESSAGE = "요청이 성공적으로 처리되었습니다.";

	private final HttpStatus httpStatus;
	private final int httpStatusCode;
	private final String message;
	private final T data;

	public ApiResponse(HttpStatus httpStatus, int httpStatusCode, String message, T data) {
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
		this.data = data;
	}

	public static <T> ResponseEntity<ApiResponse<T>> OK(T data) {
		return ResponseEntity.status(OK).body(new ApiResponse<>(OK, OK.value(), DEFAULT_MESSAGE, data));
	}

	public static <T> ResponseEntity<ApiResponse<T>> OK(String message, T data) {
		return ResponseEntity.status(OK).body(new ApiResponse<>(OK, OK.value(), message, data));
	}

	public static <T> ResponseEntity<ApiResponse<T>> CREATE(T data) {
		return ResponseEntity.status(CREATED).body(new ApiResponse<>(CREATED, CREATED.value(),  DEFAULT_MESSAGE, data));
	}

	public static <T> ResponseEntity<ApiResponse<T>> CREATE(String message, T data) {
		return ResponseEntity.status(CREATED).body(new ApiResponse<>(CREATED, CREATED.value(),  message, data));
	}

	public static <T> ResponseEntity<ApiResponse<T>> BusinessException(HttpStatus httpStatus, String message) {
		return ResponseEntity.status(httpStatus).body(new ApiResponse<>(httpStatus, httpStatus.value(), message, null));
	}
}
