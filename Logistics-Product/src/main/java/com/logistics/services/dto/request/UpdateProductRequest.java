package com.logistics.services.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
	private String name;
	private String description;
	private BigDecimal basePrice;
	private BigDecimal sellingPrice;
	private BigDecimal taxRate;
	private String status; // ACTIVE, INACTIVE, DISCONTINUED
	private Double weightKg;
	private Double lengthCm;
	private Double widthCm;
	private Double heightCm;
	private String brand;
	private Integer warrantyMonths;
}