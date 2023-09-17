package com.practice.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super("Resource Not Found on server!!!");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
