package com.logistics.services.entity;

import java.math.BigDecimal;
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
@Table(name = "refunds")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", nullable = false)
	private Payment payment;

	@Column(nullable = false)
	private String orderId;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal refundAmount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RefundStatus status; // INITIATED, APPROVED, PROCESSED, FAILED

	private String gatewayRefundId;
	private String reason;
	private String approvedBy; // manager userId for large refunds
	private LocalDateTime processedAt;
}