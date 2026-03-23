package com.logistics.services.exceptions;

public class UserNotFoundException extends UserServiceException {
   public UserNotFoundException(String identifier) {
	   super("User not found: "+identifier, "USER_NOT_FOUND",404);
   }
}
