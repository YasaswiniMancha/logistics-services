package com.logistics.services.service;

import java.util.List;
import java.util.UUID;

import com.logistics.services.dto.ProductRequestDto;
import com.logistics.services.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto request);

    ProductResponseDto getProduct(UUID productId);

    List<ProductResponseDto> getAllProducts();

}