package com.scaler.userservice_rbac.controller;

import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.models.Permission;
import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")

public class PermissionController {
    private PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    // Get all roles
    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }
    // Get All permission Attached to role
    @GetMapping("/role/{roleName}")
    public ResponseEntity<List<Permission>> getPermissionsByRole(@PathVariable String roleName) throws ResourceNotFoundException {
        List<Permission> permissions = permissionService.getPermissionsByRole(roleName);
        return ResponseEntity.ok(permissions);
    }


}
