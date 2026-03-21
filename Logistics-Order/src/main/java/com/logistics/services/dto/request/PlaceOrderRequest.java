package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
	
	@NotBlank
	private String addressId; // user's saved address UUID
	
	private String couponCode;
	private Boolean isBulkOrder = false;
}