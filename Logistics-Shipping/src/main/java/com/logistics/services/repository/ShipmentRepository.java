package com.logistics.services.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.Shipment;
import com.logistics.services.entity.ShipmentStatus;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
	
	Optional<Shipment> findByUuid(String uuid);
	
	Optional<Shipment> findByOrderId(String orderId);
	
	
	List<Shipment> findByCarrierIdAndStatus(Long carrierId, ShipmentStatus status);
	
	@Query("select s from Shipment s where "+
			"s.status='LABEL_GENERATED' AND s.createdAt<:cutoff")
	List<Shipment> findStuckShipments(@Param("cutoff") LocalDateTime cutoff);
}
