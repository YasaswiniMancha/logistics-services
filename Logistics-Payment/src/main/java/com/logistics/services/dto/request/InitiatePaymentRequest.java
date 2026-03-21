package com.logistics.services.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitiatePaymentRequest {
	
	@NotBlank
	private String orderId;
	
	@NotBlank
	private String userId;
	
	@NotNull
	private BigDecimal amount;
	
	@NotBlank
	private String gateway; // RAZORPAY, STRIPE, PAYPAL
	
	@NotBlank
	private String idempotencyKey;
}