package com.logistics.services.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.logistics.services.contracts.kafka.event.OrderCreatedEvent;
import com.logistics.services.dto.OrderRequestDto;
import com.logistics.services.dto.OrderResponseDto;
import com.logistics.services.entity.Order;
import com.logistics.services.exceptions.OrderNotFoundException;
import com.logistics.services.kafka.producer.OrderEventProducer;
import com.logistics.services.mapper.OrderMapper;
import com.logistics.services.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
    private final OrderEventProducer orderEventProducer;

	
	@Override
	public OrderResponseDto createOrder(OrderRequestDto dto) {
		  Order order = orderMapper.toEntity(dto);

	        Order savedOrder = orderRepository.save(order);

	        publishOrderCreatedEvent(savedOrder);

	        return orderMapper.toResponseDto(savedOrder);
	}


	@Override
	public OrderResponseDto getOrderById(UUID orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found") );
		return orderMapper.toResponseDto(order);
		
	}

	@Override
	public List<OrderResponseDto> getOrdersbyUser(UUID userId) {
		return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
		
	}
	
	 private void publishOrderCreatedEvent(Order order){

	        OrderCreatedEvent event = OrderCreatedEvent.builder()
	                .orderId(order.getId())
	                .userId(order.getUserId())
	                .totalAmount(order.getTotalAmount())
	                .build();

	        event.setEventId(UUID.randomUUID());
	        event.setEventType("OrderCreatedEvent");
	        event.setSourceService("main-service");
	        event.setTimestamp(LocalDateTime.now());

	        orderEventProducer.publishOrderCreatedEvent(event);
	    }
}
