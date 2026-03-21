package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAddressRequest {

	@NotBlank
	private String label;
	
	@NotBlank
	private String addressLine1;
	private String addressLine2;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String pincode;
	
	@NotBlank
	private String country;
	
	private Double latitude;
	private Double longitude;
	private Boolean isDefault = false;
}