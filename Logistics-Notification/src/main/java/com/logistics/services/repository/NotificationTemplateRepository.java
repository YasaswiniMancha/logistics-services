package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.NotificationChannel;
import com.logistics.services.entity.NotificationTemplate;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {

	Optional<NotificationTemplate> findByTemplateCodeAndChannel(String templateCode, NotificationChannel channel);

	List<NotificationTemplate> findByTargetRoleAndIsActiveTrue(String targetRole);
}
