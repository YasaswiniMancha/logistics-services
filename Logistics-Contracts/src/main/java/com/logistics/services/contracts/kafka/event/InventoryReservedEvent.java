package com.logistics.services.contracts.kafka.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryReservedEvent {

    private UUID eventId;
    private String eventType;
    private String sourceService;
    private LocalDateTime timestamp;

    private UUID orderId;
}