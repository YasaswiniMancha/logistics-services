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

// com/logistics/inventoryservice/entity/StockReservation.java
@Entity
@Table(name = "stock_reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockReservation extends BaseEntity {

	@Column(nullable = false)
	private String orderId;

	@Column(nullable = false)
	private String skuId;

	@Column(nullable = false)
	private String warehouseId;

	@Column(nullable = false)
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReservationStatus status; // RESERVED, RELEASED, CONFIRMED, EXPIRED

	private LocalDateTime expiresAt; // auto-release if payment not done within X mins
}