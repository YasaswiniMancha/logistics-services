package com.logistics.services.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.OrderCreatedEvent;
import com.logistics.services.contracts.kafka.topics.KafkaTopics;

@Service
public class OrderConsumer {

    @KafkaListener(topics = KafkaTopics.ORDER_EVENTS)
    public void handleOrderCreated(OrderCreatedEvent event){

        System.out.println("Inventory reserving for order: "
                + event.getOrderId());

    }

}