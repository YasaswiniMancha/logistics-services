package com.logistics.services.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.OrderCreatedEvent;
import com.logistics.services.contracts.kafka.topics.KafkaTopics;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event){

        kafkaTemplate.send(
                KafkaTopics.ORDER_EVENTS,
                event.getOrderId().toString(),
                event
        );

    }
}