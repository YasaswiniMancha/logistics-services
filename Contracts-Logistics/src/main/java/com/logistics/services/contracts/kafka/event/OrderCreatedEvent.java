package com.logistics.services.contracts.kafka.event;

import java.util.UUID;

import com.logistics.services.contracts.kafka.event.base.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent extends BaseEvent {

    private UUID orderId;

    private UUID userId;

    private Double totalAmount;

}