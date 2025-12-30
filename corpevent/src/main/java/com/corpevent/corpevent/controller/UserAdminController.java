package com.corpevent.corpevent.controller;


import com.corpevent.corpevent.dto.user.UserCreateDto;
import com.corpevent.corpevent.dto.user.UserDto;
import com.corpevent.corpevent.model.ValidatorHierarchy;
import com.corpevent.corpevent.repos.ValidatorHierarchyRepository;
import com.corpevent.corpevent.services.UserAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService service;
    private final ValidatorHierarchyRepository hierarchyRepo;

    @PreAuthorize("hasRole('ADMIN_USER')")
    @PostMapping
    public UserDto create(@Valid @RequestBody UserCreateDto dto){
        return service.createUser(dto);
    }

    @PreAuthorize("hasRole('ADMIN_USER')")
    @PostMapping("/organigrama")
    public ValidatorHierarchy assign(@RequestParam Long execId, @RequestParam Long sefId){
        return service.assignExecToSef(execId, sefId);
    }

    @PreAuthorize("hasRole('ADMIN_USER')")
    @GetMapping
    public List<UserDto> listAll() {
        return service.listAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN_USER')")
    @PatchMapping("/{id}/toggle")
    public UserDto toggleEnabled(@PathVariable Long id) {
        return service.toggleEnabled(id);
    }



    @PreAuthorize("hasRole('ADMIN_USER')")
    @GetMapping("/organigrama")
    public List<ValidatorHierarchy> organigrama(){
        return hierarchyRepo.findAll();
    }
}
