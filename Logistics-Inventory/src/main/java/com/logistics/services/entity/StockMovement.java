package com.logistics.services.entity;

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
@Table(name = "stock_movements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement extends BaseEntity {

	@Column(nullable = false)
	private String skuId;

	@Column(nullable = false)
	private String warehouseId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MovementType movementType; // INBOUND, OUTBOUND, ADJUSTMENT, DAMAGE, RETURN

	@Column(nullable = false)
	private Integer quantity;

	private String referenceId; // orderId, purchaseOrderId etc.
	private String performedBy; // userId
	private String notes;
}