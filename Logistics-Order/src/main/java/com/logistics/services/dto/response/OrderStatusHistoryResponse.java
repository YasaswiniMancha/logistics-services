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
public class OrderStatusHistoryResponse {

    private String      fromStatus;
    private String      toStatus;
    private String      changedBy;    // userId or "SYSTEM" for kafka-triggered transitions
    private String      remarks;
    private LocalDateTime changedAt;
}