package com.logistics.services.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String name;

    private Double price;

    private Integer quantity;

    private Boolean availability;

    private String productDescription;
}