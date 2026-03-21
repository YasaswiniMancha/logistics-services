package com.logistics.services.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.Refund;

@Repository
public interface RefundRepository {

	Optional<Refund> findByOrderId(String orderId);
	
	List<Refund> findByStatus(String status);
	
	@Query("select r from Refund r where "+
		   "r.status='INITIATED' and r.refundAmount>:threshold")	
	List<Refund> findPendingApproval(@Param("threshold") BigDecimal threshold);
	
}
