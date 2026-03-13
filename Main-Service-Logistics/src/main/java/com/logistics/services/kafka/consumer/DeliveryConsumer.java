package com.logistics.services.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.ShipmentAssignedEvent;

@Service
public class DeliveryConsumer {

    @KafkaListener(topics = "shipment-events")
    public void handleShipmentAssigned(ShipmentAssignedEvent event){

        System.out.println(
            "Order Delivered Successfully: "
            + event.getOrderId()
        );

    }
}