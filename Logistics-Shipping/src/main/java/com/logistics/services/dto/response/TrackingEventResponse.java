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
public class TrackingEventResponse {
	private String status;
	private String location;
	private String description;
	private LocalDateTime eventTime;
}