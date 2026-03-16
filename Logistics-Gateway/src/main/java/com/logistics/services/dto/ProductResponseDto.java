package com.logistics.services.dto;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private UUID id;

    private String name;

    private Double price;

    private Integer quantity;

    private Boolean availability;

    private String productDescription;
}