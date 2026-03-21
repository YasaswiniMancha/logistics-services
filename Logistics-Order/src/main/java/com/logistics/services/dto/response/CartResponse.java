package com.logistics.services.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CartResponse {
    private String userId;
    private List<CartItemResponse> items;
    private BigDecimal estimatedTotal;
    private Integer totalItems;
}