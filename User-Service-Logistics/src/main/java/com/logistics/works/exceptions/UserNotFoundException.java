package com.logistics.works.exceptions;

public class UserNotFoundException extends RuntimeException {
   public UserNotFoundException(String message) {
	   super(message);
   }
}
