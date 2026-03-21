package com.logistics.services.contracts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockCheckRequest {
	private String skuId;
	private String warehouseId; // optional, null = any warehouse
	private Integer requiredQty;
}