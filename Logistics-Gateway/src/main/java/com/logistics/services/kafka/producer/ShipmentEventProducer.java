package com.logistics.services.kafka.producer;

import java.beans.EventSetDescriptor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.ShipmentAssignedEvent;
import com.logistics.services.contracts.kafka.topics.KafkaTopics;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentEventProducer {

	public final KafkaTemplate<String , Object> kafkaTemplate;
	
	public void publishShipmentAssignedEvent(ShipmentAssignedEvent event) {
		kafkaTemplate.send(KafkaTopics.SHIPMENT_EVENTS, event.getShipmentId().toString(), event );
	}
	
}
