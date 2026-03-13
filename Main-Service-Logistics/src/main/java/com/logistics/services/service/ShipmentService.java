package com.logistics.services.service;

import java.util.UUID;

import com.logistics.services.dto.ShipmentResponseDto;

public interface ShipmentService {

    ShipmentResponseDto createShipment(UUID orderId);

    ShipmentResponseDto trackShipment(UUID orderId);

}