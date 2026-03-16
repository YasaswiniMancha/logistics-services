package com.logistics.services.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.logistics.services.entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
     private UUID orderId;
     private UUID userId;
     private Double totalAmount;
     private OrderStatus status;
     private LocalDateTime orderedAt;
}
