package com.logistics.services.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.ReservationStatus;
import com.logistics.services.entity.StockReservation;

@Repository
public interface StockReservationRepository extends JpaRepository<StockReservation, Long> {

	List<StockReservation> findByOrderId(String orderId);
	
	List<StockReservation> findByOrderIdAndStatus(String orderId, ReservationStatus status);
	
	@Query("select r from StockReservation r where " + "r.expiresAt <: now AND r.status= 'RESERVED'")
	List<StockReservation> findExpiredReservations(@Param("now") LocalDateTime now);
	
	void deleteByOrderIdAndStatus(String orderId,ReservationStatus status);
	
}
