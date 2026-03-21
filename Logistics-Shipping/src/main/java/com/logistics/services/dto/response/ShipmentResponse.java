package com.logistics.services.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ShipmentResponse {
    private String uuid;
    private String orderId;
    private String trackingNumber;
    private String carrierName;
    private String status;
    private String labelUrl;
    private BigDecimal shippingCost;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime dispatchedAt;
    private List<TrackingEventResponse> trackingHistory;
}