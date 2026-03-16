package com.logistics.services.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.logistics.services.contracts.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

	@Column(nullable = false)
	private String name;

	private Double basePrice;

	@Column(nullable = false, unique = true)
	private String sku;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false, unique = true)
	private String barcode;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal sellingPrice;

	private BigDecimal taxRate; // percentage e.g. 18.00 for GST

	@Column(nullable = false)
    private Double weightKg;

    private Double lengthCm;
    
    private Double widthCm;
    
    private Double heightCm;

    @Column(nullable = false)
    private Boolean isHazardous = false;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;  
    
    private String brand;
    
    private String manufacturer;
    
    private Integer warrantyMonths;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images = new ArrayList<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductAttribute> attributes = new ArrayList<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TierPricing> tierPricings = new ArrayList<>();
    
}
