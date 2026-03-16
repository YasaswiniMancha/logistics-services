package com.logistics.services.contracts.kafka.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.logistics.services.contracts.kafka.event.base.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCompletedEvent extends BaseEvent {

	private UUID eventId;
	private String eventType;
	private String sourceService;
	private LocalDateTime timestamp;

	private UUID paymentId;

	private UUID orderId;

	private Double amount;

}