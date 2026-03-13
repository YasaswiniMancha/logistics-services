package com.logistics.services.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.services.dto.OrderRequestDto;
import com.logistics.services.dto.OrderResponseDto;
import com.logistics.services.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
      private final OrderService orderService;
      
      @GetMapping
      public OrderResponseDto createOrder(@RequestBody OrderRequestDto dto) {
    	  return orderService.createOrder(dto);
      }
      
      @GetMapping
      public OrderResponseDto getOrderById(@PathVariable UUID orderId) {
    	  return orderService.getOrderById(orderId);
      }
      
      public List<OrderResponseDto> getOrdersByUserId(@PathVariable UUID userId) {
    	  return orderService.getOrdersbyUser(userId);
      }
}
