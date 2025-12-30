package com.corpevent.corpevent.services;

import com.corpevent.corpevent.dto.request.RequestDetailsDto;
import com.corpevent.corpevent.enums.DecisionType;
import com.corpevent.corpevent.enums.RequestStatus;
import com.corpevent.corpevent.mapper.RequestMapper;
import com.corpevent.corpevent.model.AccessCode;
import com.corpevent.corpevent.model.Request;
import com.corpevent.corpevent.model.User;
import com.corpevent.corpevent.repos.AccessCodeRepository;
import com.corpevent.corpevent.repos.EventRepository;
import com.corpevent.corpevent.repos.RequestRepository;
import com.corpevent.corpevent.repos.ValidatorHierarchyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidatorSefService {

    private final RequestRepository reqRepo;
    private final EventRepository eventRepo;
    private final AccessCodeRepository codeRepo;
    private final RequestMapper mapper;
    private final MailService mailService;
    private final ValidatorHierarchyRepository hierarchyRepo;

    public RequestDetailsDto finalDecision(Long id, DecisionType decision, String comment, User sef) {
        Request req = reqRepo.findById(id).orElseThrow();
        if(req.getStatus() != RequestStatus.L2_PRELUAT)
            throw new RuntimeException("Nu este in L2");
        req.setValidatorSef(sef);
        req.setFinalDecision(decision);
        req.setFinalComment(comment);
        req.setFinalDecisionAt(LocalDateTime.now());

        if(decision == DecisionType.POZITIV){
            req.setStatus(RequestStatus.L3_ADMIS);
            String code = UUID.randomUUID().toString().substring(0,8).toUpperCase();

            AccessCode ac = AccessCode.builder()
                    .request(req)
                    .code(code)
                    .generatedAt(LocalDateTime.now())
                    .build();

            codeRepo.save(ac);
            mailService.sendAccessCode(req.getEmail(),code);
        }else{
            req.setStatus(RequestStatus.L4_RESPINS);
        }
        reqRepo.save(req);
        int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
        return mapper.toDetailsDto(req,req.getEvent().getMaxPlaces() - approved);
    }

    public List<RequestDetailsDto> listL2(User sef) {
        List<Long> execIds = hierarchyRepo.findBySefId(sef.getId())
                .stream()
                .map(h -> h.getExec().getId())
                .toList();


        List<Request> list = reqRepo.findByStatus(RequestStatus.L2_PRELUAT)
                .stream()
                .filter(r -> execIds.contains(r.getValidatorExec().getId()))
                .toList();

        return list.stream()
                .map(req -> {
                    int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
                    return mapper.toDetailsDto(req, req.getEvent().getMaxPlaces() - approved);
                })
                .toList();
    }

//    public List<RequestDetailsDto> listL3() {
//        return reqRepo.findByStatus(RequestStatus.L3_ADMIS)
//                .stream()
//                .map(req -> {
//                    int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
//                    return mapper.toDetailsDto(req, req.getEvent().getMaxPlaces() - approved);
//                })
//                .toList();
//    }
//
//    public List<RequestDetailsDto> listL4() {
//        return reqRepo.findByStatus(RequestStatus.L4_RESPINS)
//                .stream()
//                .map(req -> {
//                    int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
//                    return mapper.toDetailsDto(req, req.getEvent().getMaxPlaces() - approved);
//                })
//                .toList();
//    }

    public List<RequestDetailsDto> listL3(User sef) {
        return listByStatus(sef, RequestStatus.L3_ADMIS);
    }

    public List<RequestDetailsDto> listL4(User sef) {
        return listByStatus(sef, RequestStatus.L4_RESPINS);
    }

    private List<RequestDetailsDto> listByStatus(User sef, RequestStatus status) {

        List<Long> execIds = hierarchyRepo.findExecIdsBySef(sef.getId());

        // dacă nu are execi → listă goală, nu null
        if (execIds.isEmpty()) {
            execIds = List.of(-1L);
        }

        return reqRepo
                .findForSefAndExecs(status, sef.getId(), execIds)
                .stream()
                .map(req -> {
                    int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
                    return mapper.toDetailsDto(
                            req,
                            req.getEvent().getMaxPlaces() - approved
                    );
                })
                .toList();
    }

}
