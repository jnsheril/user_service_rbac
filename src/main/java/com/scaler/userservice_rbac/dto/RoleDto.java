package com.scaler.userservice_rbac.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class RoleDto {
    public class RoleDTO {
        private String name;
        private Set<String> permissions;
    }
}
