package com.corpevent.corpevent.dto.event;


import com.corpevent.corpevent.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDetailsDto {
    private Long id;
    private String name;
    private EventType type;
    private String description;
    private LocalDateTime eventDate;
    private Integer maxPlaces;
}
