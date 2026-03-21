package com.logistics.services.contracts.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductSummaryDto {
    private String     productId;
    private String     skuId;
    private String     name;
    private BigDecimal sellingPrice;
    private Double     weightKg;
    private Boolean    isHazardous;
    private Boolean    inStock;        // fetched from Inventory via Product service
}