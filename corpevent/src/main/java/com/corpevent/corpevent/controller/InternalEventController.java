package com.corpevent.corpevent.controller;


import com.corpevent.corpevent.dto.event.EventStatsDto;
import com.corpevent.corpevent.services.EventAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/internal/events")
@RequiredArgsConstructor
public class InternalEventController {
    private final EventAdminService eventService;

    @PreAuthorize("hasAnyRole('ADMIN_EVENT','VALIDATOR_EXEC','VALIDATOR_SEF')")
    @GetMapping
    public List<EventStatsDto> listWithStats() {
        return eventService.stats();
    }
}
