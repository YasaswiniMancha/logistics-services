package com.logistics.services.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.StockMovement;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

	Page<StockMovement> findBySkuIdAndWarehouseIdOrderByCreatedAtDesc(String skuId, String warehouseId,
			Pageable pageable);

	List<StockMovement> findByReferenceId(String referenceId);

	@Query("select sum(q.quantity) from StockMovement where " + "m.skuId =:skuId AND m.warehouseId= :warehouseId "
			+ "AND m.movementType = 'INBOUND'")
	Integer sumInboundBySkuAndWarehouse(@Param("skuId") String skuId, @Param("warehouseId") String warehouseId);

}
