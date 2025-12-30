package com.corpevent.corpevent.dto.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventStatsDto {

    private Long eventId;
    private String eventName;
    private long l1Nepreluate;
    private long l2Preluate;
    private long l3Admise;
    private long l4Respinse;
}
