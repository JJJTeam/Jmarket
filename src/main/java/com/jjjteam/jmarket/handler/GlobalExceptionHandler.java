package com.jjjteam.jmarket.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jjjteam.jmarket.dto.ResponseDTO;

@ControllerAdvice
@RestController
//전역예외처리
public class GlobalExceptionHandler {
	
		@ExceptionHandler(value = Exception.class)
		
		public ResponseDTO<String> Exception(Exception e) {
			return new ResponseDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		
}
