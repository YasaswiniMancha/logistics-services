package com.logistics.services.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.UserAuditLog;

@Repository
public interface UserAuditLogRepository {

	Page<UserAuditLog> findByUserUuidOrderbyCreatedAtDesc(String userUuid, Pageable pageable);
	
	List<UserAuditLog> findByUserUuidAndAction(String userUuid, String action);
}
