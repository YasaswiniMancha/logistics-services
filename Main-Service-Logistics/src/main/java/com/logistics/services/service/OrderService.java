package com.logistics.services.service;

import java.util.List;
import java.util.UUID;

import com.logistics.services.dto.OrderRequestDto;
import com.logistics.services.dto.OrderResponseDto;

public interface OrderService {
	
    OrderResponseDto createOrder(OrderRequestDto dto);
    
    OrderResponseDto getOrderById(UUID orderId);
    
    List<OrderResponseDto> getOrdersbyUser(UUID userId);
}
