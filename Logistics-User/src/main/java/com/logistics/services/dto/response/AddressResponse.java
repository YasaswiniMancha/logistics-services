package com.logistics.services.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
	private String uuid;
	private String label;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pincode;
	private String country;
	private Boolean isDefault;
}
