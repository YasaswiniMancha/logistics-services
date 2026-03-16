package com.logistics.services.entity;

import java.time.LocalDate;

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

@Entity
@Table(name = "delivery_routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryRoute extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agent_id", nullable = false)
	private DeliveryAgent agent;

	private LocalDate routeDate;
	private Integer totalStops;

	@Column(columnDefinition = "TEXT")
	private String routePolyline; // encoded Google Maps polyline

	private Double totalDistanceKm;
	private Integer estimatedMinutes;
}
