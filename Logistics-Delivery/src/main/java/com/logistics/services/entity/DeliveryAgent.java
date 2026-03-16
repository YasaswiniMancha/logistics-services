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
@Table(name = "delivery_agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAgent extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String userId; // reference to User service (DELIVERY_AGENT role)

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AgentStatus status; // AVAILABLE, ON_DELIVERY, OFF_DUTY, INACTIVE

	private String vehicleType; // BIKE, VAN, TRUCK
	private String vehicleNumber;

	private String currentWarehouseId;
	private Double currentLatitude;
	private Double currentLongitude;
	private LocalDateTime locationUpdatedAt;

	// Performance stats
	private Integer totalDeliveries;
	private Integer successfulDeliveries;
	private Double onTimeDeliveryRate;
	private Double averageRating;
}