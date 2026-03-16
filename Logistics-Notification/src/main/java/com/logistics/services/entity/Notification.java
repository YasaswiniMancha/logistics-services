package com.logistics.services.entity;

import java.time.LocalDateTime;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private String recipientEmail;

	private String recipientPhone;
	private String deviceToken; // for push notifications

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationChannel channel;

	@Column(nullable = false)
	private String subject;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String body;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationStatus status; // PENDING, SENT, FAILED, SKIPPED

	private String triggerEvent; // e.g. "order.placed"
	private String referenceId; // orderId, paymentId etc.

	private String failureReason;
	private Integer retryCount = 0;
	private LocalDateTime sentAt;

	private String eventId; // Kafka event ID — for idempotency
}
