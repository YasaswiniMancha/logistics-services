package com.logistics.services.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {
	private String uuid;
	private String channel;
	private String subject;
	private String status;
	private String triggerEvent;
	private String referenceId;
	private LocalDateTime sentAt;
	private LocalDateTime createdAt;
}