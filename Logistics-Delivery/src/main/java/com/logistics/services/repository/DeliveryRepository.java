package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logistics.services.entity.Delivery;
import com.logistics.services.entity.DeliveryAgent;
import com.logistics.services.entity.DeliveryStatus;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	Optional<Delivery> findByUuid(String uuid);

	Optional<Delivery> findByOrderId(String orderId);

	Optional<Delivery> findByShipmentId(String shipmentId);

	List<DeliveryAgent> findByAgentIdAndStatus(Long agentId, DeliveryStatus status);

	@Query("select d from Delivery d where " +
			"a.status='AVAILABLE' and a.currentWarehouseId=:warehouseId")
	List<DeliveryAgent> findAvailableAgents(@Param("warehouseId") String warehouseId);

	@Query("select d from Delivery d where " + 
			"d.status='ASSIGNED' AND d.agent is NULL")
	List<Delivery> findUnassignedDeliveries();
}
