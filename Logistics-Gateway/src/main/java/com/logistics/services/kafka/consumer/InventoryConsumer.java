package com.logistics.services.kafka.consumer;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.InventoryReservedEvent;
import com.logistics.services.contracts.kafka.event.OrderCreatedEvent;
import com.logistics.services.kafka.producer.InventoryEventProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {

	private final InventoryEventProducer producer;

    @KafkaListener(topics = "order-events")
    public void handleOrderCreated(OrderCreatedEvent event){

        InventoryReservedEvent inventoryEvent =
                InventoryReservedEvent.builder()
                        .eventId(UUID.randomUUID())
                        .eventType("InventoryReservedEvent")
                        .sourceService("main-service")
                        .timestamp(LocalDateTime.now())
                        .orderId(event.getOrderId())
                        .build();

        producer.publishInventoryReservedEvent(inventoryEvent);
    }
}
