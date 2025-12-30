package com.corpevent.corpevent.controller;


import com.corpevent.corpevent.dto.request.RequestDetailsDto;
import com.corpevent.corpevent.dto.user.UserDto;
import com.corpevent.corpevent.enums.DecisionType;
import com.corpevent.corpevent.mapper.UserMapper;
import com.corpevent.corpevent.model.User;
import com.corpevent.corpevent.model.ValidatorHierarchy;
import com.corpevent.corpevent.repos.ValidatorHierarchyRepository;
import com.corpevent.corpevent.security.CorpeventUserDetails;
import com.corpevent.corpevent.services.ValidatorSefService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internal/sef")
@RequiredArgsConstructor
public class SefController {
    private final ValidatorSefService sefService;
    private final ValidatorHierarchyRepository hierarchyRepo;
    private final UserMapper userMapper;

    @PreAuthorize("hasRole('VALIDATOR_SEF')")
    @PostMapping("/requests/{id}/final")
    public RequestDetailsDto finalDecision(
            @PathVariable Long id,
            @RequestParam DecisionType decision,
            @RequestParam String comment,
            @AuthenticationPrincipal CorpeventUserDetails sef
            ){
        return sefService.finalDecision(id, decision, comment, sef.getUser());

    }

    @PreAuthorize("hasRole('VALIDATOR_SEF')")
    @GetMapping("/requests/l2")
    public List<RequestDetailsDto> listL2(@AuthenticationPrincipal CorpeventUserDetails sef) {
        return sefService.listL2(sef.getUser());
    }

//    @PreAuthorize("hasRole('VALIDATOR_SEF')")
//    @GetMapping("/requests/l3")
//    public List<RequestDetailsDto> listL3() {
//        return sefService.listL3();
//    }
//
//    @PreAuthorize("hasRole('VALIDATOR_SEF')")
//    @GetMapping("/requests/l4")
//    public List<RequestDetailsDto> listL4() {
//        return sefService.listL4();
//    }

    @GetMapping("/requests/l3")
    @PreAuthorize("hasRole('VALIDATOR_SEF')")
    public List<RequestDetailsDto> l3(Authentication auth) {
        User sef = ((CorpeventUserDetails) auth.getPrincipal()).getUser();
        return sefService.listL3(sef);
    }

    @GetMapping("/requests/l4")
    @PreAuthorize("hasRole('VALIDATOR_SEF')")
    public List<RequestDetailsDto> l4(Authentication auth) {
        User sef = ((CorpeventUserDetails) auth.getPrincipal()).getUser();
        return sefService.listL4(sef);
    }


    @GetMapping("/organigrama")
    @PreAuthorize("hasRole('VALIDATOR_SEF')")
    public List<UserDto> organigrama(@AuthenticationPrincipal CorpeventUserDetails sef) {
        return hierarchyRepo.findBySefId(sef.getUser().getId())
                .stream()
                .map(ValidatorHierarchy::getExec)
                .map(userMapper::toDto)
                .toList();
    }

}
