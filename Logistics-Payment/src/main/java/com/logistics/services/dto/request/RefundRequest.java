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
public class RefundRequest {

	@NotBlank
	private String orderId;

	@NotNull
	private BigDecimal refundAmount;

	@NotBlank
	private String reason;
}