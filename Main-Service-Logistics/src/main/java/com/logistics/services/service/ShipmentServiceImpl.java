package com.logistics.services.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logistics.services.dto.ShipmentResponseDto;
import com.logistics.services.entity.Shipment;
import com.logistics.services.entity.ShipmentStatus;
import com.logistics.services.repository.ShipmentRepository;
import com.logistics.services.service.ShipmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepo;

    @Override
    public ShipmentResponseDto createShipment(UUID orderId) {

        Shipment shipment = Shipment.builder()
                .orderId(orderId)
                .carrier("DHL")
                .trackingNumber("TRK-" + System.currentTimeMillis())
                .status(ShipmentStatus.CREATED)
                .build();

        Shipment saved = shipmentRepo.save(shipment);

        return map(saved);
    }

    @Override
    public ShipmentResponseDto trackShipment(UUID orderId) {

        Shipment shipment = shipmentRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        return map(shipment);
    }

    private ShipmentResponseDto map(Shipment shipment) {

        return ShipmentResponseDto.builder()
                .shipmentId(shipment.getId())
                .orderId(shipment.getOrderId())
                .carrier(shipment.getCarrier())
                .trackingNumber(shipment.getTrackingNumber())
                .status(shipment.getStatus())
                .build();
    }
}