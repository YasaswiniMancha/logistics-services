package com.logistics.services.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundResponse {
	private String uuid;
	private BigDecimal refundAmount;
	private String status;
	private String reason;
	private LocalDateTime processedAt;
}