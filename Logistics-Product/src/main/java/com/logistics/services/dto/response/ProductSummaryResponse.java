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
public class ProductSummaryResponse {
	private String uuid;
	private String name;
	private String sku;
	private BigDecimal sellingPrice;
	private String primaryImageUrl;
	private String status;
	private Boolean inStock;
}