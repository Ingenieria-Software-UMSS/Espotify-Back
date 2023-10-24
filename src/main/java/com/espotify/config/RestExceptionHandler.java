package com.espotify.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.espotify.dto.ErrorDto;
import com.espotify.exceptions.AppException;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value = { AppException.class })
	@ResponseBody
	public ResponseEntity<ErrorDto> handleException(AppException appException) {
		return ResponseEntity.status(appException.getHttpStatus())
				.body(ErrorDto.builder().message(appException.getMessage()).build());
	}
}
