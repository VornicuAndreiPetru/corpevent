package com.corpevent.corpevent.services;


import com.corpevent.corpevent.dto.user.UserCreateDto;
import com.corpevent.corpevent.dto.user.UserDto;
import com.corpevent.corpevent.enums.RoleName;
import com.corpevent.corpevent.mapper.UserMapper;
import com.corpevent.corpevent.model.Role;
import com.corpevent.corpevent.model.User;
import com.corpevent.corpevent.model.ValidatorHierarchy;
import com.corpevent.corpevent.repos.RoleRepository;
import com.corpevent.corpevent.repos.UserRepository;
import com.corpevent.corpevent.repos.ValidatorHierarchyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final ValidatorHierarchyRepository hierarchyRepo;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;
    public UserDto createUser(UserCreateDto dto) {

        if (userRepo.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username-ul este deja folosit");
        }

        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email-ul este deja folosit");
        }
        Role role = roleRepo.findByName(dto.getRole()).orElseThrow();
        User u = User.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .enabled(true)
                .roles(new HashSet<>())
                .build();
        u.getRoles().add(role);
        userRepo.save(u);
        return mapper.toDto(u);
    }

    @Transactional
    public ValidatorHierarchy assignExecToSef(Long execId, Long sefId) {

        User exec = userRepo.findById(execId)
                .orElseThrow(() -> new RuntimeException("Exec inexistent"));

        User sef = userRepo.findById(sefId)
                .orElseThrow(() -> new RuntimeException("Șef inexistent"));

        if (!exec.hasRole(RoleName.VALIDATOR_EXEC)) {
            throw new RuntimeException("User-ul ales ca exec NU are rol VALIDATOR_EXEC");
        }

        if (!sef.hasRole(RoleName.VALIDATOR_SEF)) {
            throw new RuntimeException("User-ul ales ca șef NU are rol VALIDATOR_SEF");
        }


        ValidatorHierarchy h = hierarchyRepo
                .findByExecId(execId)
                .orElse(
                        ValidatorHierarchy.builder()
                                .exec(exec)
                                .build()
                );

        h.setSef(sef);
        return hierarchyRepo.save(h);
    }

    public List<UserDto> listAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public UserDto toggleEnabled(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getUsername().equals("admin")) {
            throw new RuntimeException("Nu poți dezactiva adminul principal");
        }

        user.setEnabled(!user.isEnabled());
        return mapper.toDto(user);
    }

}
