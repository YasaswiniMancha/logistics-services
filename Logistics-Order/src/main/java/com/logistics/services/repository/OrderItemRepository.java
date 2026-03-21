package com.logistics.services.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findByOrderUuid(String orderUuid);
	
	@Query("select i productId, SUM(i.quality) as sold FROM OrderItem i "+
	"GROUP BY i.productId ORDER BY sold desc")
	List<Object[]> findTopSellingProducts(Pageable pageable);
	
}
