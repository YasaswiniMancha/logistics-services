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
public class DeliveryAttemptResponse {
	private Integer attemptNumber;
	private String status;
	private String failureReason;
	private LocalDateTime attemptedAt;
	private LocalDateTime nextAttemptScheduled;
}