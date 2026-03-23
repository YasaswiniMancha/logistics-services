package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateLocationRequest {

    @NotNull private Double latitude;
    @NotNull private Double longitude;
}