package com.logistics.services.entity;

import java.math.BigDecimal;

import com.logistics.services.contracts.entity.BaseEntity;

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
@Table(name = "tier_pricings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TierPricing extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Enumerated(EnumType.STRING)
	private CustomerTier customerTier; // SILVER, GOLD, PLATINUM

	private Integer minQuantity; // bulk pricing threshold
	private BigDecimal discountPercent;
	private BigDecimal finalPrice;
}
