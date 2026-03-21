package com.logistics.services.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryResponse {
	private String uuid;
	private String orderId;
	private String shipmentId;
	private String agentName;
	private String agentPhone;
	private String status;
	private LocalDateTime scheduledSlotStart;
	private LocalDateTime scheduledSlotEnd;
	private LocalDateTime completedAt;
	private List<DeliveryAttemptResponse> attempts;
}