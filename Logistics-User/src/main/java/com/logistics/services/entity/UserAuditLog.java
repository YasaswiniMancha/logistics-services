package com.logistics.services.entity;

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
@Table(name = "user_audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuditLog {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String action; // Login, Logout, Deactivate etc

	private String performedBy;

	private String ipAddress;

	private String deviceInfo;

	private String description;

}
