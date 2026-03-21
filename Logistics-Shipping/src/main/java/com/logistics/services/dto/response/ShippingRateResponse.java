package com.logistics.services.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingRateResponse {
	private String carrierId;
	private String carrierName;
	private BigDecimal cost;
	private Integer estimatedDays;
	private String serviceType; // STANDARD, EXPRESS, OVERNIGHT
}