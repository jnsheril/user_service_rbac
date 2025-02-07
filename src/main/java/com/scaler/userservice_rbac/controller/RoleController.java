package com.scaler.userservice_rbac.controller;

import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.repository.RoleRepository;
import com.scaler.userservice_rbac.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class RoleController {
    private RoleRepository roleRepository;
    private RoleService roleService;

    public RoleController(RoleRepository roleRepository,RoleService roleService){
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    // Get all roles
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }


}

