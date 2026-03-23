package com.logistics.services.exceptions;

public class AccountDeactivatedException extends UserServiceException {
	public AccountDeactivatedException() {
		super("Account has been deactivated. Contact support.", "ACCOUNT_DEACTIVATED", 403);
	}
}