package com.corpevent.corpevent.controller;


import com.corpevent.corpevent.dto.request.RequestDetailsDto;
import com.corpevent.corpevent.enums.DecisionType;
import com.corpevent.corpevent.security.CorpeventUserDetails;
import com.corpevent.corpevent.services.ValidatorExecService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internal/exec")
public class ExecController {

    private final ValidatorExecService execService;

    @PreAuthorize("hasRole('VALIDATOR_EXEC')")
    @PostMapping("/requests/{id}/take")
    public RequestDetailsDto take(@PathVariable Long id, @AuthenticationPrincipal CorpeventUserDetails exec){
        return execService.takeRequest(id,exec.getUser());
    }


    @PreAuthorize("hasRole('VALIDATOR_EXEC')")
    @GetMapping("/requests/l1")
    public List<RequestDetailsDto> list(){
        return execService.list();
    }

    @PreAuthorize("hasRole('VALIDATOR_EXEC')")
    @GetMapping("/requests/l2")
    public List<RequestDetailsDto> listL2(@AuthenticationPrincipal CorpeventUserDetails exec){
        return execService.listL2(exec.getUser());
    }


    @PreAuthorize("hasRole('VALIDATOR_EXEC')")
    @PostMapping("/requests/{id}/preliminary")
    public RequestDetailsDto preliminary(
            @PathVariable Long id,
            @RequestParam DecisionType decision,
            @RequestParam String comment,
            @AuthenticationPrincipal CorpeventUserDetails exec
            ){
        return execService.preliminaryDecision(id, decision,comment,exec.getUser());
    }
}
