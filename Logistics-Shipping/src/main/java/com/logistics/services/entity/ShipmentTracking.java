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

// com/logistics/shippingservice/entity/ShipmentTracking.java
@Entity
@Table(name = "shipment_tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentTracking extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id", nullable = false)
	private Shipment shipment;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String statusDescription;

	@Enumerated(EnumType.STRING)
	private ShipmentStatus status;

	private LocalDateTime eventTime;
	private String carrierRawStatus; // raw status string from carrier API
}
