package com.corpevent.corpevent.mapper;


import com.corpevent.corpevent.dto.request.RequestCreateDto;
import com.corpevent.corpevent.dto.request.RequestDetailsDto;
import com.corpevent.corpevent.dto.request.RequestSummaryDto;
import com.corpevent.corpevent.enums.RequestStatus;
import com.corpevent.corpevent.model.Event;
import com.corpevent.corpevent.model.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final EventMapper eventMapper;

    public Request toEntity(RequestCreateDto dto, Event event){
        return Request.builder()
                .event(event)
                .requesterName(dto.getRequesterName())
                .company(dto.getCompany())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .createdAt(LocalDateTime.now())
                .status(RequestStatus.L1_NEPRELUAT)
                .clientStatus(dto.getClientStatus())
                .build();
    }

    public RequestSummaryDto toSummaryDto(Request r){
        return RequestSummaryDto.builder()
                .id(r.getId())
                .requesterName(r.getRequesterName())
                .company(r.getCompany())
                .email(r.getEmail())
                .status(r.getStatus())
                .eventId(r.getEvent().getId())
                .build();
    }

    public RequestDetailsDto toDetailsDto(Request r, int availablePlaces){
        String accessCode = r.getAccessCode() == null
                ? null
                : r.getAccessCode().getCode();



        String execName = null;
        if(r.getValidatorExec() != null){
            execName = r.getValidatorExec().getFirstname() + " " +
                    r.getValidatorExec().getLastname();
        }

        String sefName = null;
        if(r.getValidatorSef() != null){
            sefName = r.getValidatorSef().getFirstname() + " " +
                    r.getValidatorSef().getLastname();
        }

        return RequestDetailsDto.builder()
                .id(r.getId())
                .requesterName(r.getRequesterName())
                .company(r.getCompany())
                .phone(r.getPhone())
                .email(r.getEmail())
                .status(r.getStatus())
                .event(eventMapper.toPublicDto(r.getEvent(),availablePlaces))
                .preliminaryDecision(r.getPreliminaryDecision())
                .preliminaryComment(r.getPreliminaryComment())
                .finalDecision(r.getFinalDecision())
                .finalComment(r.getFinalComment())
                .accessCode(accessCode)
                .execName(execName)
                .sefName(sefName)
                .takenAt(r.getTakenAt())
                .clientStatus(r.getClientStatus())
                .build();
    }
}
