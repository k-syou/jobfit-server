package com.jobfit.server.support.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobfit.server.interfaces.api.ApiResponse;
import com.jobfit.server.support.exception.BusinessException;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> BusinessExceptionHandler(BusinessException e) {
		return ApiResponse.BusinessException(e.getHttpStatus(), e.getMessage());
	}
}
