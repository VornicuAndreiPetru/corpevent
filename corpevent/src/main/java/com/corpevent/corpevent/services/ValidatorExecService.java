package com.corpevent.corpevent.services;

import com.corpevent.corpevent.dto.request.RequestDetailsDto;
import com.corpevent.corpevent.enums.ClientStatus;
import com.corpevent.corpevent.enums.DecisionType;
import com.corpevent.corpevent.enums.EventType;
import com.corpevent.corpevent.enums.RequestStatus;
import com.corpevent.corpevent.mapper.RequestMapper;
import com.corpevent.corpevent.model.AccessCode;
import com.corpevent.corpevent.model.Request;
import com.corpevent.corpevent.model.User;
import com.corpevent.corpevent.repos.AccessCodeRepository;
import com.corpevent.corpevent.repos.EventRepository;
import com.corpevent.corpevent.repos.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidatorExecService {

    private final RequestRepository reqRepo;
    private final EventRepository eventRepo;
    private final RequestMapper mapper;
    private final AccessCodeRepository codeRepo;
    private final MailService mailService;
    public RequestDetailsDto takeRequest(Long id, User exec) {
        Request req = reqRepo.findById(id).orElseThrow();
        if(req.getStatus()!= RequestStatus.L1_NEPRELUAT)
            throw new RuntimeException("Nu este in L1");
        req.setStatus(RequestStatus.L2_PRELUAT);
        req.setValidatorExec(exec);
        req.setTakenAt(LocalDateTime.now());
        reqRepo.save(req);
        int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
        return mapper.toDetailsDto(req,req.getEvent().getMaxPlaces() - approved);
    }

    public RequestDetailsDto preliminaryDecision(
            Long id,
            DecisionType decision,
            String comment,
            User exec
    ) {
        Request req = reqRepo.findById(id).orElseThrow();

        if (!exec.getId().equals(req.getValidatorExec().getId()))
            throw new RuntimeException("Nu este preluată de tine.");

        boolean execCanDecideFinal =
                req.getClientStatus() == ClientStatus.CLIENT_EXISTENT
                        && req.getEvent().getType() == EventType.ACTUALIZARI_MAJORE;

        if (execCanDecideFinal) {
            req.setFinalDecision(decision);
            req.setFinalComment(comment);
            req.setFinalDecisionAt(LocalDateTime.now());
            req.setValidatorSef(null);

            if (decision == DecisionType.POZITIV) {
                req.setStatus(RequestStatus.L3_ADMIS);
                String code = UUID.randomUUID().toString().substring(0,8).toUpperCase();

                AccessCode ac = AccessCode.builder()
                        .request(req)
                        .code(code)
                        .generatedAt(LocalDateTime.now())
                        .build();
                codeRepo.save(ac);
                mailService.sendAccessCode(req.getEmail(),code);
            } else {
                req.setStatus(RequestStatus.L4_RESPINS);
            }

        } else {
            req.setPreliminaryDecision(decision);
            req.setPreliminaryComment(comment);
            req.setStatus(RequestStatus.L2_PRELUAT);
        }

        reqRepo.save(req);

        int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
        return mapper.toDetailsDto(req, req.getEvent().getMaxPlaces() - approved);
    }


    public List<RequestDetailsDto> list() {
        return reqRepo.findByStatus(RequestStatus.L1_NEPRELUAT)
                .stream()
                .map(req -> {
                    int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
                    return mapper.toDetailsDto(req, req.getEvent().getMaxPlaces() - approved);
                })
                .toList();
    }

    public List<RequestDetailsDto> listL2(User exec) {
        return reqRepo.findByStatusAndValidatorExec_Id(RequestStatus.L2_PRELUAT, exec.getId())
                .stream()
                .map(req -> {
                    int approved = eventRepo.countApprovedRequestsByEvent(req.getEvent().getId());
                    return mapper.toDetailsDto(req, req.getEvent().getMaxPlaces() - approved);
                })
                .toList();
    }

}
