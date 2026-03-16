package com.logistics.services.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
	
	private String accessToken;
	
	private String refreshToken;
	
	@Builder.Default
	private String tokenType = "Bearer";
	
	private UUID userId;
	
	private String username;
	
	 private Set<String> roles;
	// private String
}
