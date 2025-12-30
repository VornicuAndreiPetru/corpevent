package com.corpevent.corpevent.services;


import com.corpevent.corpevent.dto.event.EventPublicDto;
import com.corpevent.corpevent.dto.request.RequestCreateDto;
import com.corpevent.corpevent.dto.request.RequestDetailsDto;
import com.corpevent.corpevent.mapper.EventMapper;
import com.corpevent.corpevent.mapper.RequestMapper;
import com.corpevent.corpevent.model.Event;
import com.corpevent.corpevent.model.Request;
import com.corpevent.corpevent.repos.EventRepository;
import com.corpevent.corpevent.repos.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicService {

    private final EventRepository eventRepo;
    private final RequestRepository requestRepo;
    private final EventMapper eventMapper;
    private final RequestMapper requestMapper;
    public List<EventPublicDto> getUpcomingEvents() {
        return eventRepo.findUpcomingEvents().stream()
                .map(e-> {
                    int approved = eventRepo.countApprovedRequestsByEvent(e.getId());
                    return eventMapper.toPublicDto(e,e.getMaxPlaces() - approved);
                })
                .toList();
    }

    public RequestDetailsDto createRequest(RequestCreateDto dto) {
        Event event = eventRepo.findById(dto.getEventId()).orElseThrow();

        int approved = eventRepo.countApprovedRequestsByEvent(event.getId());
        if(approved >= event.getMaxPlaces())
            throw new RuntimeException("Nu mai sunt locuri");

        Request r = requestMapper.toEntity(dto, event);
        requestRepo.save(r);
        return requestMapper.toDetailsDto(r, event.getMaxPlaces() - approved);
    }

    public EventPublicDto getEvent(Long id) {
        Event e = eventRepo.findById(id).orElseThrow();
        int approved = eventRepo.countApprovedRequestsByEvent(id);
        return eventMapper.toPublicDto(e, e.getMaxPlaces() - approved);
    }
}
