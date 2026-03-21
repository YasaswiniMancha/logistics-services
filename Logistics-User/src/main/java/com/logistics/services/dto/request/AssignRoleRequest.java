package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignRoleRequest {

	@NotBlank
	private String userId;
	
	@NotBlank
	private String role; // must be a valid UserRole value
}