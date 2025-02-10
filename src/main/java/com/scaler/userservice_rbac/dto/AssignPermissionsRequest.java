package com.scaler.userservice_rbac.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class AssignPermissionsRequest {
    //userID
    private Long id;
    private String roleName;
    private List<String> permissionNames;
}

