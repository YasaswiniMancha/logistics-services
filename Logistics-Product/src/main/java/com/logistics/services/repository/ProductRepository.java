package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.Product;
import com.logistics.services.entity.ProductStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByUuid(String uuid);

	Optional<Product> findBySku(String sku);

	Optional<Product> findByBarcode(String barcode);

	boolean existsBySku(String sku);

	boolean existsByBarcode(String barcode);
	
	Page<Product> findByCategoryUuidAndStatusAndDeletedFalse(String categoryUuid, ProductStatus status, Pageable pageable);
	
	@Query("select p from Product p WHERE " + "LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%')) OR " + "LOWER(p.brand) LIKE LOWER(CONCAT('%', :q, '%')) " + "AND p.status= 'ACTIVE' AND p.deleted= false")
	Page<Product> searchProducts(@Param("q") String query, Pageable pageable);

	@Query("select p from Product p WHERE p.isHazardous = true AND p.status = 'ACTIVE'")
	List<Product> findAllHazardous();
	
	List<Product> findByUuidIn(List<String> uuids);
}
