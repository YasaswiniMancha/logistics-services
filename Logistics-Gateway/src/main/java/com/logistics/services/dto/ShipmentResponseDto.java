package com.logistics.services.dto;

import java.util.UUID;

import com.logistics.services.entity.ShipmentStatus;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponseDto {

    private UUID shipmentId;

    private UUID orderId;

    private String carrier;

    private String trackingNumber;

    private ShipmentStatus status;
}