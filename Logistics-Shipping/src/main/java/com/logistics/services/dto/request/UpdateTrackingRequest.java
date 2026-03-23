package com.logistics.services.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateTrackingRequest {

    @NotBlank private String trackingNumber;
    @NotBlank private String status;
    @NotBlank private String location;
    @NotBlank private String statusDescription;
    private LocalDateTime eventTime;
}