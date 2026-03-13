package com.logistics.works.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	 @Value("${app.kafka.topic.user-created}")
	 private String topic;
	 
	public void publishUserCreatedEvent(UserCreatedEvent event) {
		kafkaTemplate.send(topic, 
				event.getId().toString(),  // message key
				event);
	}
	
}

