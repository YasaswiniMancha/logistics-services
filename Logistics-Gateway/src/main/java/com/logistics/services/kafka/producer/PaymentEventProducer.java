package com.logistics.services.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.PaymentCompletedEvent;
import com.logistics.services.contracts.kafka.topics.KafkaTopics;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentEventProducer {

	public final KafkaTemplate<String,Object> kafkaTemplate;
	
	public void publishPaymentCompletedEvent(PaymentCompletedEvent event) {
		kafkaTemplate.send(KafkaTopics.PAYMENT_EVENTS,
				event.getPaymentId().toString(),
				event);
	}

}
