package com.logistics.services.dto.request;
// REQUEST DTOs

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStockRequest {

	@NotBlank
	private String skuId;

	@NotBlank
	private String productId;

	@NotBlank
	private String warehouseId;

	@NotNull
	@Min(1)
	private Integer quantity;

	private Integer lowStockThreshold;
	private Integer reorderPoint;
	private Integer reorderQuantity;
	private String binLocation;
}