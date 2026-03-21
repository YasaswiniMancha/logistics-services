package com.logistics.services.contracts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCheckResponse {
	private String skuId;
	private String warehouseId;
	private Integer availableQty;
	private Boolean sufficient;
}