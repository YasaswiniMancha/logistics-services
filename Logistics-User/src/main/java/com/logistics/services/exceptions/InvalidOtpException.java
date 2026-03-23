package com.logistics.services.exceptions;

public class InvalidOtpException extends UserServiceException {
    public InvalidOtpException() {
        super("OTP is invalid or has expired", "INVALID_OTP", 400);
    }
}