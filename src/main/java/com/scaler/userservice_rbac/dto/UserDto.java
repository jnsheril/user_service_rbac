package com.scaler.userservice_rbac.dto;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class UserDto {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
