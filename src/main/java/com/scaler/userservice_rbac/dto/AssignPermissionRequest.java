package com.scaler.userservice_rbac.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AssignPermissionRequest {
    private Long id;
    private String roleName;
    private String permissionName;
}

