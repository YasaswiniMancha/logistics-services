package com.logistics.services.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logistics.services.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findByUuid(String uuid);

	Optional<Payment> findByOrderId(String orderId);

	Optional<Payment> findByIdempotencyKey(String idempotencyKey);

	Optional<Payment> findByGatewayPaymentId(String gatewayPaymentId);

	boolean existsByOrderId(String orderId);

	List<Payment> findByUserIdAndStatus(String orderId, String status);

	@Query("select sum(p.amount) from Payment p where " + 
		   "p.status='SUCCESS' AND p.paidAt BETWEEN :from AND :to")
	BigDecimal sumSuccessfulPayments(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
