package com.logistics.services.entity;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory", uniqueConstraints = @UniqueConstraint(columnNames = { "sku_id", "warehouse_id" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory extends BaseEntity {

	@Column(nullable = false)
	private String skuId; // matches Product.sku

	@Column(nullable = false)
	private String productId; // matches Product.uuid

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id", nullable = false)
	private Warehouse warehouse;

	@Column(nullable = false)
	private Integer totalQuantity;

	@Column(nullable = false)
	private Integer reservedQuantity = 0;

	@Column(nullable = false)
	private Integer availableQuantity; // totalQuantity - reservedQuantity

	@Column(nullable = false)
	private Integer lowStockThreshold;

	@Column(nullable = false)
	private Integer reorderPoint;

	@Column(nullable = false)
	private Integer reorderQuantity;

	private String binLocation; // e.g. "A-12-B"
	private String shelfLocation;

	@Version // optimistic locking — prevents overselling
	private Long version;
}