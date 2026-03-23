package com.logistics.services.exceptions;

public class InvalidCredentialsException extends UserServiceException {
	public InvalidCredentialsException(String message) {
		super("Invalid email or password", "INVALID_CREDENTIALS", 401);
	}
}