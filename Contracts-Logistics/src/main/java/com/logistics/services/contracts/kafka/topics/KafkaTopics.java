package com.logistics.services.contracts.kafka.topics;

public class KafkaTopics {

    public static final String USER_EVENTS = "user-events";

    public static final String ORDER_EVENTS = "order-events";

    public static final String PAYMENT_EVENTS = "payment-events";

    public static final String SHIPMENT_EVENTS = "shipment-events";

    public static final String DELIVERY_EVENTS = "delivery-events";

    public static final String INVENTORY_EVENTS = "inventory-events";

}

//One topic per domain
//Multiple event types inside topic