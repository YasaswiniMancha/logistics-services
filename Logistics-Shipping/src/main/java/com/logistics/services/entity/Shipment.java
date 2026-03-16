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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String orderId;

	@Column(nullable = false, unique = true)
	private String trackingNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrier_id", nullable = false)
	private Carrier carrier;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ShipmentStatus status; // CREATED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, RETURNED

	private String labelUrl; // S3 link to shipping label PDF/ZPL
	private String manifestId;

	// Package dimensions snapshot
	private Double totalWeightKg;
	private Double lengthCm;
	private Double widthCm;
	private Double heightCm;

	@Column(precision = 10, scale = 2)
	private BigDecimal shippingCost;

	private LocalDateTime estimatedDeliveryDate;
	private LocalDateTime dispatchedAt;
	private LocalDateTime deliveredAt;

	// Ship-to address snapshot
	private String recipientName;
	private String addressLine1;
	private String city;
	private String state;
	private String pincode;
	private String country;
	private String phone;

	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ShipmentTracking> trackingHistory = new ArrayList<>();
}