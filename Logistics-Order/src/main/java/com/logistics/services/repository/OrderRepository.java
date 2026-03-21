package com.logistics.services.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logistics.services.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByUuid(String uuid);
	// Optional because we are Looking for/expecting 0 or 1 item (by ID, by unique
	// key, login, etc.)

	Optional<Order> findByOrderNumber(String orderNumber);

	Page<Order> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);
	// When using pagination + total count (full metadata: total pages, total
	// elements)
	/*
	 * Returned automatically by Spring Data when method accepts Pageable. Use when
	 * frontend needs "Page 3 of 12" info.
	 */

	@Query("select o from Order o where o.slaDeadline<:now "
			+ "AND o.status NOT IN('DELIVERED', 'CANCELLED', 'REFUNDED')")
	List<Order> findSlaBreachOrders(@Param("now") LocalDateTime now);

	@Query("select o from Order o WHERE o.userId=:userId " + "AND o.status=:status order by o.createdAt DESC")
	List<Order> findByUserIdAndStatus(@Param("userId") String userId, @Param("status") String status);

	@Query("SELECT SUM(o.totalAmount) FROM Order o WHERE "
			+ "o.status= 'DELIVERED' AND o.createdAt BETWEEN :from AND :to")
	BigDecimal sumRevenueInPeriod(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
