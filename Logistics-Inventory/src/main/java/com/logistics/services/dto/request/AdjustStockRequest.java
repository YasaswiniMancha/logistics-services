package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdjustStockRequest {
	
	@NotBlank
	private String skuId;

	@NotBlank
	private String warehouseId;
	
	@NotNull
	private Integer adjustmentQty; // positive = add, negative = remove
	
	@NotBlank
	private String movementType; // ADJUSTMENT, DAMAGE, RETURN
	
	private String notes;
	
}