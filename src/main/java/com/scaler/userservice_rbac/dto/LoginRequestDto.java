package com.scaler.userservice_rbac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequestDto {
    private String username;
    private String password;
}
