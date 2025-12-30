package com.corpevent.corpevent.dto.event;

import com.corpevent.corpevent.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventPublicDto {
    private Long id;
    private String name;
    private EventType type;
    private LocalDateTime eventDate;
    private Integer availablePlaces;
}
