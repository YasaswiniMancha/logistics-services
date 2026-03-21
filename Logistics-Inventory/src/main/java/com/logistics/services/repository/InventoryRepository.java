package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuIdAndWarehouseUuid(String skuId, String warehouseUuid);
	
	List<Inventory> findBySkuId(String skuId);
	
	@Query("select i from Inventory i where i.skuId= :skuId" + " AND i.availableQuantity >=:requiredQty" + " AND i.deleted=false ORDER BY i.availableQuantity DESC")
	List<Inventory> findSufficientStock(@Param("skuId") String skuId , @Param("requiredQty") int requiredQty);
	
	@Query("select i from Inventory i where " + "i.availableQuantity<= i.lowThreshold AND i.deleted=false")
	List<Inventory> findAllBelowThreshold();
	
	@Query("select i from Inventory i where i.skuId IN: skuIds AND i.deleted = false")
	List<Inventory> findBySkuIds(@Param("skuIds") List<String> skuIds);
}
