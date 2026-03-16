package com.logistics.services.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {
//for signup/update
	
   @NotBlank
   private String username;
   
   @NotBlank
   @Email
   private String email;
   
   @NotBlank
   private String password;
   
   @NotBlank
   private String firstName;
   
   @NotBlank
   private String lastName;
   
   @NotBlank
   private String phoneNumber;
   
   @NotBlank
   private String address;
   
   @NotBlank
   private String state;
   
   @NotBlank
   private String country;
}
