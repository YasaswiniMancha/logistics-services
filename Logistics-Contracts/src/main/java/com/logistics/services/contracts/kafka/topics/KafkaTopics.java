package com.logistics.services.contracts.kafka.topics;

public final class KafkaTopics {
 private KafkaTopics() {}

 // Order topics
 public static final String ORDER_PLACED         = "order.placed";
 public static final String ORDER_CANCELLED      = "order.cancelled";
 public static final String ORDER_STATUS_UPDATED = "order.status.updated";

 // Payment topics
 public static final String PAYMENT_SUCCESS  = "payment.success";
 public static final String PAYMENT_FAILED   = "payment.failed";
 public static final String PAYMENT_REFUNDED = "payment.refunded";

 // Inventory topics
 public static final String INVENTORY_RESERVED  = "inventory.reserved";
 public static final String INVENTORY_RELEASED  = "inventory.released";
 public static final String INVENTORY_LOW_STOCK = "inventory.low.stock";

 // Shipment topics
 public static final String SHIPMENT_CREATED    = "shipment.created";
 public static final String SHIPMENT_DISPATCHED = "shipment.dispatched";

 // Delivery topics
 public static final String DELIVERY_COMPLETED        = "delivery.completed";
 public static final String DELIVERY_FAILED           = "delivery.failed";
 public static final String DELIVERY_LOCATION_UPDATED = "delivery.location.updated";

 // User topics
 public static final String USER_REGISTERED           = "user.registered";
 public static final String USER_VERIFIED             = "user.verified";
 public static final String USER_ROLE_CHANGED         = "user.role.changed";
 public static final String USER_DEACTIVATED          = "user.deactivated";
 public static final String USER_ADDRESS_UPDATED      = "user.address.updated";
 public static final String USER_PASSWORD_RESET       = "user.password.reset";
 public static final String USER_LOGIN_SUSPICIOUS     = "user.login.suspicious";
 public static final String USER_PREFERENCES_UPDATED  = "user.preferences.updated";

 // Product topics
 public static final String PRODUCT_UPDATED = "product.updated";
 public static final String PRODUCT_DELETED = "product.deleted";

 // Notification topic
 public static final String NOTIFICATION_SEND = "notification.send";
}