package com.logistics.services.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class FailDeliveryRequest {

    @NotBlank private String deliveryId;
    @NotBlank private String reason;          // NOT_HOME, WRONG_ADDRESS, REFUSED
    private LocalDateTime nextAttemptSlot;
}