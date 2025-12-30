package com.corpevent.corpevent.mapper;


import com.corpevent.corpevent.dto.event.EventCreateDto;
import com.corpevent.corpevent.dto.event.EventDetailsDto;
import com.corpevent.corpevent.dto.event.EventPublicDto;
import com.corpevent.corpevent.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event toEntity(EventCreateDto dto){
        return Event.builder()
                .name(dto.getName())
                .type(dto.getType())
                .description(dto.getDescription())
                .eventDate(dto.getEventDate())
                .maxPlaces(dto.getMaxPlaces())
                .build();
    }

    public EventDetailsDto toDetailsDto(Event e){
        return EventDetailsDto.builder()
                .id(e.getId())
                .name(e.getName())
                .type(e.getType())
                .description(e.getDescription())
                .eventDate(e.getEventDate())
                .maxPlaces(e.getMaxPlaces())
                .build();
    }

    public EventPublicDto toPublicDto(Event e, int available){
        return EventPublicDto.builder()
                .id(e.getId())
                .name(e.getName())
                .type(e.getType())
                .eventDate(e.getEventDate())
                .availablePlaces(available)
                .build();
    }
}
