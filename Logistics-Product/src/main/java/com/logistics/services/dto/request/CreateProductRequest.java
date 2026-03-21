package com.logistics.services.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

	@NotBlank
	private String name;
	private String description;
	
	@NotBlank
	private String sku;
	
	@NotBlank
	private String barcode;
	
	@NotBlank
	private String categoryId;

	@NotNull
	@DecimalMin("0.0")
	private BigDecimal basePrice;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal sellingPrice;
	
	private BigDecimal taxRate;

	@NotNull
	@DecimalMin("0.0")
	private Double weightKg;
	
	private Double lengthCm;
	private Double widthCm;
	private Double heightCm;

	private Boolean isHazardous = false;
	private String brand;
	private String manufacturer;
	private Integer warrantyMonths;

	private List<Map<String, String>> attributes; // [{key:"Color", value:"Red"}]
}
