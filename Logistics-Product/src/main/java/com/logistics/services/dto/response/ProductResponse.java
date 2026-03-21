package com.logistics.services.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
	private String uuid;
	private String name;
	private String description;
	private String sku;
	private String barcode;
	private CategoryResponse category;
	private BigDecimal basePrice;
	private BigDecimal sellingPrice;
	private BigDecimal taxRate;
	private Double weightKg;
	private Double lengthCm;
	private Double widthCm;
	private Double heightCm;
	private Boolean isHazardous;
	private String status;
	private String brand;
	private Integer warrantyMonths;
	private List<String> imageUrls;
	private List<Map<String, String>> attributes;
	private Integer stockCount; // fetched from Inventory via Feign
	private LocalDateTime createdAt;
}