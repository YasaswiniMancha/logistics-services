package com.logistics.services.kafka.consumer;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.InventoryReservedEvent;
import com.logistics.services.contracts.kafka.event.PaymentCompletedEvent;
import com.logistics.services.kafka.producer.PaymentEventProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {
//Consumes inventory event
	private final PaymentEventProducer producer;
	
    @KafkaListener(topics = "inventory-events")
	public void handleInventoryReserved(InventoryReservedEvent event) {
	  PaymentCompletedEvent paymentEvent =
		PaymentCompletedEvent.builder()
		.eventId(UUID.randomUUID())
		.eventType("PaymentCompletedEvent")
		.sourceService("main-service")
		.timestamp(LocalDateTime.now())
		.orderId(event.getOrderId())
		.build();
		
	 producer.publishPaymentCompletedEvent(paymentEvent);
		
	}
	
	
}
