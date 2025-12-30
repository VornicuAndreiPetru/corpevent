package com.corpevent.corpevent.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private Set<String> roles;
}
