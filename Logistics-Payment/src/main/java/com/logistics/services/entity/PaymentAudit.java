package com.logistics.services.entity;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// com/logistics/paymentservice/entity/PaymentAudit.java
@Entity
@Table(name = "payment_audits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentAudit extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", nullable = false)
	private Payment payment;

	@Column(nullable = false)
	private String event; // PAYMENT_INITIATED, WEBHOOK_RECEIVED etc.

	@Column(columnDefinition = "TEXT")
	private String requestPayload;

	@Column(columnDefinition = "TEXT")
	private String responsePayload;

	private String sourceIp;
}
