package com.logistics.services.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AddToCartRequest {
    @NotBlank private String productId;
    @NotBlank private String skuId;
    @NotNull @Min(1) private Integer quantity;
}