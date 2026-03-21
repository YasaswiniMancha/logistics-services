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
public class AgentLocationResponse {
	private String agentId;
	private String agentName;
	private Double latitude;
	private Double longitude;
	private LocalDateTime updatedAt;
}