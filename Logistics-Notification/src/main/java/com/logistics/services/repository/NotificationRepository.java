package com.logistics.services.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	Page<Notification> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);
	
	Boolean existsByEventId(String eventId);
	
	List<Notification> findByStatusAndRetryCountLessThan(String status, int maxRetries);
	
	@Query("select n from Notification n where "+
	"n.status='FAILED' "+
	"AND n.retryCount<3 AND n.createdAt>:since")
	List<Notification> findRetryableNotifications(@Param("since") LocalDateTime since);
	
}
