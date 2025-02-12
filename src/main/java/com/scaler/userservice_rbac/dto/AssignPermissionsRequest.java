package com.scaler.userservice_rbac.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor

public class AssignPermissionsRequest {
    //userID
    private Long userId;
    private String roleName;
    private List<Map<String,String>> permissions;
}

