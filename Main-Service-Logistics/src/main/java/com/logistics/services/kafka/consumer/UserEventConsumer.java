package com.logistics.services.kafka.consumer;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEventConsumer {
   
	public void consumeUserCreatedEvent(UserCreatedEvent event) {
		
	}
}
