package com.corpevent.corpevent.controller;


import com.corpevent.corpevent.dto.auth.LoginRequest;
import com.corpevent.corpevent.dto.auth.LoginResponse;
import com.corpevent.corpevent.model.User;
import com.corpevent.corpevent.repos.UserRepository;
import com.corpevent.corpevent.security.CorpeventUserDetailsService;
import com.corpevent.corpevent.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final CorpeventUserDetailsService userDetailsService;
    private final UserRepository repository;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails details = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(details);

        User user = repository.findByUsername(request.getUsername()).orElseThrow();

        return new LoginResponse(
                token,
                user.getUsername(),
                user.getRoles().stream().map(r->r.getName().name()).collect(Collectors.toSet())
        );
    }
}
