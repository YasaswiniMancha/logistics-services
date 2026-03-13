package com.logistics.services.kafka.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.logistics.services.kafka.event.base.BaseEvent;

import lombok.*;

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