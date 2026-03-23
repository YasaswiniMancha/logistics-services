package com.logistics.services.exceptions;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {

	private final String errorCode;
	private final int httpStatus;

	public UserServiceException(String message, String errorCode, int httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

}
