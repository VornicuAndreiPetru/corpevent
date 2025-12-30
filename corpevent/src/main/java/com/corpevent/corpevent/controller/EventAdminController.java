package com.corpevent.corpevent.controller;


import com.corpevent.corpevent.dto.event.EventCreateDto;
import com.corpevent.corpevent.dto.event.EventDetailsDto;
import com.corpevent.corpevent.dto.event.EventStatsDto;
import com.corpevent.corpevent.services.EventAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/events")
@RequiredArgsConstructor
public class EventAdminController {

    private final EventAdminService eventService;

    @PreAuthorize("hasRole('ADMIN_EVENT')")
    @PostMapping
    public EventDetailsDto create(@Valid @RequestBody EventCreateDto dto){
        return eventService.create(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN_EVENT','VALIDATOR_EXEC', 'VALIDATOR_SEF')")
    @GetMapping
    public List<EventDetailsDto> list(){
        return eventService.list();
    }

    @PreAuthorize("hasRole('ADMIN_EVENT')")
    @PutMapping("/{id}")
    public EventDetailsDto update(@PathVariable Long id, @RequestBody EventCreateDto dto){
        return eventService.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN_EVENT')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        eventService.delete(id);
    }


}
