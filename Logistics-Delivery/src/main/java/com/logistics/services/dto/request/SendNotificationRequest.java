package com.logistics.services.dto.request;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SendNotificationRequest {

    @NotBlank private String userId;
    @NotBlank private String channel;         // EMAIL, SMS, PUSH
    @NotBlank private String templateCode;
    private Map<String, String> variables;    // template placeholders
}