package com.corpevent.corpevent.dto.request;


import com.corpevent.corpevent.enums.RequestStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestSummaryDto {
    private Long id;
    private String requesterName;
    private String company;
    private String email;
    private RequestStatus status;
    private Long eventId;
}
