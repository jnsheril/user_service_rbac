package com.scaler.userservice_rbac.service;

import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.exceptions.UnauthorizedException;
import com.scaler.userservice_rbac.models.Permission;
import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.models.User;
import com.scaler.userservice_rbac.repository.PermissionRepository;
import com.scaler.userservice_rbac.repository.RoleRepository;
import com.scaler.userservice_rbac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository,
       UserRepository userRepository,PermissionRepository permissionRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }
    //Get All Role
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    //Assign Permission to Role - if its Admin
    public void assignPermissionToRole(Long id, String roleName, String permissionName) throws ResourceNotFoundException, UnauthorizedException {
        // find admin user
        User adminUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));
        // check if found user is Admin
        boolean isAdmin = adminUser.getRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase("ADMIN"));

        if (!isAdmin) {
            throw new UnauthorizedException("Only Admin can assign permissions to roles");
        }

        // Fetch Role
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        // Fetch Permission
        Permission permission = permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        // Assign Permission to Role
        role.getPermissions().add(permission);
        roleRepository.save(role);
    }
}




