package com.scaler.userservice_rbac.controller;

import com.scaler.userservice_rbac.dto.AssignPermissionsRequest;
import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.exceptions.UnauthorizedException;
import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")

public class RoleController {
    private RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    /* Get all roles */
    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
    /* assign Multiple permission to roles */
    @PostMapping("/assign-permissions")
    public ResponseEntity<String> assignPermissionsToRole(@RequestBody AssignPermissionsRequest request)
            throws UnauthorizedException, ResourceNotFoundException {
        if (request.getId() == null || request.getRoleName() == null || request.getPermissionNames() == null || request.getPermissionNames().isEmpty()) {
            return ResponseEntity.badRequest().body("Missing required fields!");
        }
        roleService.assignPermissionsToRole(request.getId(), request.getRoleName(), request.getPermissionNames());
        return ResponseEntity.ok("Permissions assigned successfully!");
    }
}



