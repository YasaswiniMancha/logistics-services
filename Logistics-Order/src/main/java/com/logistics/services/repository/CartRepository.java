package com.logistics.services.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logistics.services.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Optional<Cart> findByUserId(String userId);
	
	@Query("select c from Cart c where c.expiresAt=:now")
	List<Cart> findExpiredCarts(@Param("now") LocalDateTime now);
	
}
