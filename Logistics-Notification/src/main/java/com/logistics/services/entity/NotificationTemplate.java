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
@Table(name = "notification_templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationTemplate extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String templateCode; // e.g. ORDER_PLACED_CUSTOMER, LOW_STOCK_MANAGER

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationChannel channel; // EMAIL, SMS, PUSH

	@Column(nullable = false)
	private String targetRole; // CUSTOMER, MANAGER, WAREHOUSE_STAFF etc.

	private String subject; // for EMAIL

	@Column(nullable = false, columnDefinition = "TEXT")
	private String bodyTemplate; // Thymeleaf/Mustache template with {{variables}}

	private Boolean isActive = true;
}