package com.logistics.services.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.logistics.services.kafka.event.UserCreatedEvent;

@Service
public class UserEventConsumer {

    @KafkaListener(
        topics = "user-created-topic",
        groupId = "logistics-service-group"
    )
    public void consume(UserCreatedEvent event) {

        System.out.println("User created: " + event.getUsername());

    }

}