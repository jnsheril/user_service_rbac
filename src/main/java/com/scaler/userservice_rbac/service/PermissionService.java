package com.scaler.userservice_rbac.service;

import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.models.Permission;
import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.repository.PermissionRepository;
import com.scaler.userservice_rbac.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;
    public PermissionService(PermissionRepository permissionRepository,RoleRepository roleRepository){
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }
    public List<Permission> getAllPermissions(){
        List<Permission> permissions = permissionRepository.findAll();
        return permissions;
    }

    public List<Permission> getPermissionsByRole(String roleName) throws ResourceNotFoundException {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        return new ArrayList<>(role.getPermissions());
    }
}
