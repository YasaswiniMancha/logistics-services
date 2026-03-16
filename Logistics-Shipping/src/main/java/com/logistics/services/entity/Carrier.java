package com.logistics.services.entity;

import java.math.BigDecimal;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// com/logistics/shippingservice/entity/Carrier.java
@Entity
@Table(name = "carriers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrier extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String code; // FEDEX, DHL, DELHIVERY, BLUEDART

	@Column(nullable = false)
	private String name;

	private String apiEndpoint;
	private String apiKey; // encrypted at rest
	private Boolean isActive;

	private BigDecimal baseCostPerKg;
	private Integer maxWeightKg;
	private Integer avgDeliveryDays;
}