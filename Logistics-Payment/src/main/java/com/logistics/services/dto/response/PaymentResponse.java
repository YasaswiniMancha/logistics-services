package com.logistics.services.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
	private String uuid;
	private String orderId;
	private BigDecimal amount;
	private String status;
	private String gateway;
	private String gatewayOrderId;
	private String gatewayPaymentId;
	private LocalDateTime paidAt;
	private List<RefundResponse> refunds;
}