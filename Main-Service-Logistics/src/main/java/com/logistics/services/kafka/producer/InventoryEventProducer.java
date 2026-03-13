package com.logistics.services.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.InventoryReservedEvent;
import com.logistics.services.contracts.kafka.topics.KafkaTopics;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	
public class InventoryEventProducer {

    private final KafkaTemplate<String,Object> kafkaTemplate;
	
	public void publishInventoryReservedEvent(InventoryReservedEvent event) {
	    kafkaTemplate.send(
	    		KafkaTopics.INVENTORY_EVENTS,
	    		event.getOrderId().toString(),
	    		event
	    		);
	}

}
