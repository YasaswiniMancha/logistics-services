package com.logistics.services.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
	private String uuid;
	private String skuId;
	private String productId;
	private String warehouseId;
	private String warehouseName;
	private Integer totalQuantity;
	private Integer reservedQuantity;
	private Integer availableQuantity;
	private Integer lowStockThreshold;
	private Integer reorderPoint;
	private String binLocation;
	private Boolean isLowStock;
}