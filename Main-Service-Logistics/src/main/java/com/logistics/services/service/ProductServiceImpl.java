package com.logistics.services.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.logistics.services.dto.ProductRequestDto;
import com.logistics.services.dto.ProductResponseDto;
import com.logistics.services.entity.Product;
import com.logistics.services.mapper.ProductMapper;
import com.logistics.services.repository.ProductRepository;
import com.logistics.services.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto request) {

        Product product = productMapper.toEntity(request);

        Product saved = productRepo.save(product);

        return productMapper.toDto(saved);
    }

    @Override
    public ProductResponseDto getProduct(UUID productId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {

        return productRepo.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}