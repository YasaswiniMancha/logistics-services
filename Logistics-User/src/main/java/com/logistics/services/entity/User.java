package com.logistics.services.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{

	@Column(unique=true, nullable=false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(unique=true, nullable=false)
	private String phone;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(nullable=false, name="role")
	@Builder.Default
	@CollectionTable(name="user_roles",joinColumns=@JoinColumn(name="user_id"))
	private Set<Roles> roles = new HashSet<>();  
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@Column(nullable=false)
	private LocalDateTime updatedAt;
	
	@Enumerated(EnumType.STRING)
    private CustomerTier tier;   // STANDARD, SILVER, GOLD, PLATINUM (for CUSTOMER role)
	
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;   // UNVERIFIED, ACTIVE, DEACTIVATED, SUSPENDED

	private String profileImageUrl;
	
	private String otp;
	
	private LocalDateTime otpExpiresAt;
	
	private String passwordResetToken;
	
	private LocalDateTime passwordResetExpiresAt;
	
	private Integer failedLoginAttempts;
	
	private LocalDateTime lockedUntil;
	
	private BigDecimal totalSpent;
	
	private Integer totalOrders;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Address> addresses = new ArrayList<>();
    
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<UserAuditLog> auditLogs = new ArrayList<>();

	@OneToOne(mappedBy = "user",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private RefreshToken refreshToken;
	

    // For OAuth2 users: "google", "github" — null for local users
    private String provider;

    // OAuth2 provider's user ID (Google sub, GitHub id)
    private String providerId;
}
