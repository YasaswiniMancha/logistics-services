package com.logistics.services.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// com/logistics/paymentservice/entity/Payment.java
@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String idempotencyKey; // prevents duplicate charges

	@Column(nullable = false, unique = true)
	private String orderId;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus status; // INITIATED, PENDING, SUCCESS, FAILED, REFUNDED

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentGateway gateway; // RAZORPAY, STRIPE, PAYPAL

	private String gatewayOrderId; // gateway's own order reference
	private String gatewayPaymentId; // gateway's payment ID on success
	private String gatewaySignature; // HMAC signature from webhook

	private String failureReason;
	private LocalDateTime paidAt;

	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Refund> refunds = new ArrayList<>();

	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PaymentAudit> auditTrail = new ArrayList<>();
}