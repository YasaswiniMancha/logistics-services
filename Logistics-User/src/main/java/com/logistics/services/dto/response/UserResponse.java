package com.logistics.services.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
	private String uuid;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String role;
	private String status;
	private String tier;
	private String profileImageUrl;
	private Integer totalOrders;
	private BigDecimal totalSpend;
	private LocalDateTime createdAt;
	private List<AddressResponse> addresses;
}