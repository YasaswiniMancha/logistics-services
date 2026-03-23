package com.logistics.services.exceptions;

public class AccountNotVerifiedException extends UserServiceException {
	public AccountNotVerifiedException() {
		super("Account is not verified. Please verify your email first.", "ACCOUNT_NOT_VERIFIED", 403);
	}
}