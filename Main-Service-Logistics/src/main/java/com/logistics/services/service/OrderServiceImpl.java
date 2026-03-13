package com.logistics.services.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.logistics.services.dto.OrderRequestDto;
import com.logistics.services.dto.OrderResponseDto;
import com.logistics.services.entity.Order;
import com.logistics.services.mapper.OrderMapper;
import com.logistics.services.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	
	@Override
	public OrderResponseDto createOrder(OrderRequestDto dto) {
	   Order order = orderMapper.toEntity(dto);
	   Order savedOrder = orderRepository.save(order);
	   return orderMapper.toResponseDto(savedOrder);
	}

	@Override
	public OrderResponseDto getOrderById(UUID orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found "));
		return orderMapper.toResponseDto(order);
		
	}

	@Override
	public List<OrderResponseDto> getOrdersbyUser(UUID userId) {
		return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
		
	}
}
