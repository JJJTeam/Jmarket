package com.jjjteam.jmarket;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


//게시판 리스트에서id값에 해당하는 값이없을때 예외처리 

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public DataNotFoundException(String message) {
		super(message);
	}
}
