package com.logistics.services.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CompleteDeliveryRequest {

    @NotBlank private String deliveryId;
    @NotBlank private String otp;             // customer OTP or
    private String signatureImageUrl;         // S3 URL of signature
    private String deliveryNotes;
}