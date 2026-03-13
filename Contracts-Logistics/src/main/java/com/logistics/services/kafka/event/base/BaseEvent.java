package com.logistics.services.kafka.event.base;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEvent {

    private UUID eventId;

    private String eventType;

    private String eventVersion;

    private String sourceService;

    private LocalDateTime timestamp;

}