package com.corpevent.corpevent.mapper;


import com.corpevent.corpevent.dto.user.UserDto;
import com.corpevent.corpevent.model.Role;
import com.corpevent.corpevent.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(User u){

        return UserDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .email(u.getEmail())
                .enabled(u.isEnabled())
                .roles(u.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .build();
    }
}
