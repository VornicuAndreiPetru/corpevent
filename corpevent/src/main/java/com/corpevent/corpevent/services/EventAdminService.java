package com.corpevent.corpevent.services;


import com.corpevent.corpevent.dto.event.EventCreateDto;
import com.corpevent.corpevent.dto.event.EventDetailsDto;
import com.corpevent.corpevent.dto.event.EventStatsDto;
import com.corpevent.corpevent.enums.RequestStatus;
import com.corpevent.corpevent.mapper.EventMapper;
import com.corpevent.corpevent.model.Event;
import com.corpevent.corpevent.repos.EventRepository;
import com.corpevent.corpevent.repos.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventAdminService {

    private final EventMapper mapper;
    private final EventRepository repository;
    private final RequestRepository reqRepo;

    public List<EventDetailsDto> list() {
        return repository.findAll().stream().map(mapper::toDetailsDto).toList();
    }

    public EventDetailsDto create(EventCreateDto dto) {
        Event e = mapper.toEntity(dto);
        repository.save(e);
        return mapper.toDetailsDto(e);
    }


    @Transactional
    public EventDetailsDto update(Long id, EventCreateDto dto) {
        Event e = repository.findById(id).orElseThrow(()->new RuntimeException("Event not found"));
        e.setName(dto.getName());
        e.setType(dto.getType());
        e.setDescription(dto.getDescription());
        e.setEventDate(dto.getEventDate());
        e.setMaxPlaces(dto.getMaxPlaces());
        return mapper.toDetailsDto(e);
    }

    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Event not found");
        }
        repository.deleteById(id);
    }

    public List<EventStatsDto> stats() {
        return repository.findAll().stream()
                .map(event -> EventStatsDto.builder()
                        .eventId(event.getId())
                        .eventName(event.getName())
                        .l1Nepreluate(
                                reqRepo.countByEventIdAndStatus(event.getId(), RequestStatus.L1_NEPRELUAT)
                        )
                        .l2Preluate(
                                reqRepo.countByEventIdAndStatus(event.getId(), RequestStatus.L2_PRELUAT)
                        )
                        .l3Admise(
                                reqRepo.countByEventIdAndStatus(event.getId(), RequestStatus.L3_ADMIS)
                        )
                        .l4Respinse(
                                reqRepo.countByEventIdAndStatus(event.getId(), RequestStatus.L4_RESPINS)
                        )
                        .build()
                )
                .toList();
    }
}
