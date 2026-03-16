package com.logistics.services.entity;

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
@Table(name = "deliveries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String orderId;

	@Column(nullable = false, unique = true)
	private String shipmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agent_id")
	private DeliveryAgent agent;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DeliveryStatus status; // ASSIGNED, IN_PROGRESS, COMPLETED, FAILED, RESCHEDULED

	// Delivery address snapshot
	private String recipientName;
	private String recipientPhone;
	private String addressLine1;
	private String city;
	private String pincode;
	private Double latitude;
	private Double longitude;

	private LocalDateTime scheduledSlotStart;
	private LocalDateTime scheduledSlotEnd;
	private LocalDateTime assignedAt;
	private LocalDateTime completedAt;

	private String proofOfDelivery; // OTP used or signature S3 URL
	private String deliveryNotes;

	private Integer maxAttempts = 3;

	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DeliveryAttempt> attempts = new ArrayList<>();
}