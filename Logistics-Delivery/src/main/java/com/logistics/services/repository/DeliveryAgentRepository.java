package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.AgentStatus;
import com.logistics.services.entity.DeliveryAgent;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {

	Optional<DeliveryAgent> findByUserId(String userId);
	
	Optional<DeliveryAgent> findByUuid(String uuid);
	
	List<DeliveryAgent> findByStatus(AgentStatus status);
	
	List<DeliveryAgent> findByStatusAndCurrentWarehouseId(AgentStatus status, String warehouseId);
	
	@Query(value="select d from delivery_agents where status='AVAILABLE' "+
	"ORDER BY (POW(current_latitude-:lat, 2) + POW(current_longitude - :lng, 2))"+
	"LIMIT :limit", nativeQuery = true)
	List<DeliveryAgent> findNearestAvailableAgents(@Param("lat") Double lat, @Param("lng") Double lng, @Param("limit") int limit);	
}
