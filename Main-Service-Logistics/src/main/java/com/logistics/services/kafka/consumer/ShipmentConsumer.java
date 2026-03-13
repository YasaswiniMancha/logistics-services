package com.logistics.services.kafka.consumer;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.PaymentCompletedEvent;
import com.logistics.services.contracts.kafka.event.ShipmentAssignedEvent;
import com.logistics.services.kafka.producer.ShipmentEventProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentConsumer {
	
	private final ShipmentEventProducer producer;
	
	public void handlePaymentCompleted(PaymentCompletedEvent event) {
		ShipmentAssignedEvent shipmentEvent = ShipmentAssignedEvent.builder()
				.eventId(UUID.randomUUID())
				.eventType("ShipmentAssignedEvent")
				.sourceService("main-service")
				.timestamp(LocalDateTime.now())
				.orderId(event.getOrderId())
				.build();
		producer.publishShipmentAssignedEvent(shipmentEvent);
	}
	
}
