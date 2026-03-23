package com.logistics.services.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateShipmentRequest {

	@NotBlank
	private String orderId;
	
	@NotBlank
	private String carrierCode; // FEDEX, DHL, DELHIVERY

	// Package details
	@NotNull
	@DecimalMin("0.01")
	private Double totalWeightKg;
	
	private Double lengthCm;
	private Double widthCm;
	private Double heightCm;

	// Recipient snapshot
	@NotBlank
	private String recipientName;
	
	@NotBlank
	private String recipientPhone;
	
	@NotBlank
	private String addressLine1;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String pincode;
	
	@NotBlank
	private String country;
}