package com.corpevent.corpevent.dto.request;


import com.corpevent.corpevent.dto.event.EventPublicDto;
import com.corpevent.corpevent.enums.ClientStatus;
import com.corpevent.corpevent.enums.DecisionType;
import com.corpevent.corpevent.enums.RequestStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestDetailsDto {
    private Long id;
    private String requesterName;
    private String company;
    private String phone;
    private String email;
    private RequestStatus status;
    private EventPublicDto event;
    private DecisionType preliminaryDecision;
    private String preliminaryComment;
    private DecisionType finalDecision;
    private String finalComment;
    private String accessCode;
    private String execName;
    private String sefName;
    private LocalDateTime takenAt;
    private ClientStatus clientStatus;
}
