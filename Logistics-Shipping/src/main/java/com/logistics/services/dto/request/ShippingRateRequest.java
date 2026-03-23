package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ShippingRateRequest {

    @NotNull  private Double weightKg;
    @NotBlank private String originPincode;
    @NotBlank private String destinationPincode;
    private Boolean isHazardous = false;
}