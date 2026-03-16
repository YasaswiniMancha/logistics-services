package com.logistics.services.entity;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "warehouses")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Warehouse extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;           // e.g. WH-HYD-01

    @Column(nullable = false)
    private String name;

    private String addressLine1;
    private String city;
    private String state;
    private String pincode;
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private WarehouseStatus status;  // ACTIVE, INACTIVE

    private String managerUserId;    // reference to User service
}