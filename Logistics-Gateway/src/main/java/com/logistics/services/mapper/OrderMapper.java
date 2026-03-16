package com.logistics.services.mapper;

import org.springframework.stereotype.Component;

import com.logistics.services.dto.OrderRequestDto;
import com.logistics.services.dto.OrderResponseDto;
import com.logistics.services.entity.Order;
import com.logistics.services.entity.OrderStatus;

@Component
public class OrderMapper {

	public Order toEntity(OrderRequestDto dto) {
		return Order.builder()
				.userId(dto.getUserId())
				.itemsSubtotal(dto.getItemsSubtotal())
				.totalAmount(dto.getTotalAmount())
				.status(OrderStatus.CREATED).build();
	}

	public OrderResponseDto toResponseDto(Order order) {
		return OrderResponseDto.builder()
		.orderId(order.getId())
		.userId(order.getUserId())
		.totalAmount(order.getTotalAmount())
		.status(order.getStatus())
		.orderedAt(order.getOrderedAt())
		.build();
	}
}
