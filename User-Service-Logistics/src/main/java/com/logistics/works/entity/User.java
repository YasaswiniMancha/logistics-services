package com.logistics.works.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private UUID id;
	
	@Column(unique=true, nullable=false)
	private String username;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique=true, nullable=false)
	private String phoneNumber;
	
	@Column(nullable=false)
	private String address;
	
	@Column(nullable=false)
	private String state;
	
	
	@Column(nullable=false)
	private String country;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="user_roles")
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, name="role")
	private Set<Roles> roles = new HashSet<>();
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@Column(nullable=false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
