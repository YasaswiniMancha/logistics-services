package com.logistics.services.entity;

import java.time.LocalDateTime;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAttempt extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id", nullable = false)
	private Delivery delivery;

	@Column(nullable = false)
	private Integer attemptNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AttemptStatus status; // SUCCESS, FAILED_NOT_HOME, FAILED_WRONG_ADDRESS, FAILED_REFUSED

	private LocalDateTime attemptedAt;
	private String failureReason;
	private LocalDateTime nextAttemptScheduled;

	private Double agentLatitude; // agent's GPS at time of attempt
	private Double agentLongitude;
}