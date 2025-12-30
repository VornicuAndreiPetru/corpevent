package com.corpevent.corpevent.dto.user;


import com.corpevent.corpevent.enums.RoleName;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private boolean enabled;
    private Set<RoleName> roles;
}
