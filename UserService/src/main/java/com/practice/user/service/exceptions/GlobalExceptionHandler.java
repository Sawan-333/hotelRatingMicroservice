package com.practice.user.service.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleRecordNotFoundException(ResourceNotFoundException ex){
		String msg=ex.getMessage();
		return msg;	
	}

}
