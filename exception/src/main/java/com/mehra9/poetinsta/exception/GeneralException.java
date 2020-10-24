package com.mehra9.poetinsta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {

	public GeneralException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
