package com.logistics.services.mapper;

import org.springframework.stereotype.Component;

import com.logistics.services.dto.ProductRequestDto;
import com.logistics.services.dto.ProductResponseDto;
import com.logistics.services.entity.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDto dto) {

        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .availability(dto.getAvailability())
                .productDescription(dto.getProductDescription())
                .build();
    }

    public ProductResponseDto toDto(Product product) {

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .availability(product.getAvailability())
                .productDescription(product.getProductDescription())
                .build();
    }
}