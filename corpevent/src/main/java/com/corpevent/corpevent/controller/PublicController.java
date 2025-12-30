package com.corpevent.corpevent.controller;


import com.corpevent.corpevent.dto.event.EventPublicDto;
import com.corpevent.corpevent.dto.request.RequestCreateDto;
import com.corpevent.corpevent.dto.request.RequestDetailsDto;
import com.corpevent.corpevent.services.PublicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {
    private final PublicService service;

    @GetMapping("/events")
    public List<EventPublicDto> listEvents(){
        return service.getUpcomingEvents();
    }

    @GetMapping("/events/{id}")
    public EventPublicDto getEvent(@PathVariable Long id){
        return service.getEvent(id);
    }


    @PostMapping("/requests")
    public RequestDetailsDto createRequest(@Valid @RequestBody RequestCreateDto dto){
        return service.createRequest(dto);
    }
}
