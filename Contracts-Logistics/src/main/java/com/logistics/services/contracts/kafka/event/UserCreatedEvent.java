package com.logistics.services.contracts.kafka.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.logistics.services.contracts.kafka.event.base.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent extends BaseEvent  {

    private UUID id;

    private String username;

    private String email;
    
    private String phoneNumber;

    private LocalDateTime createdAt;

}