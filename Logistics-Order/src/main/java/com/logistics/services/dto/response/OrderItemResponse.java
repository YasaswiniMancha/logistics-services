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
public class OrderItemResponse {
	
	private String productId;
	
	private String skuId;
	private String productName;
	private Integer quantity;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
}