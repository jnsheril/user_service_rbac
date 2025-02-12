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
import java.util.*;

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
    /* Get All Role */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    /* Assign Permission to Role - if its Admin */
    public void assignPermissionsToRole(Long adminId, String roleName, List<Map<String,String>> permissionList)
            throws ResourceNotFoundException, UnauthorizedException {

        // Fetch Role
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        // Fetch Permissions
        Set<Permission> permissionsToAdd = new HashSet<>();
        for (Map<String,String> permissionMap : permissionList) {
                String resource = permissionMap.get("resource");
                String action = permissionMap.get("action");
            Permission permission = permissionRepository.findByResourceAndAction(resource, action)
                    .orElseThrow(() -> new ResourceNotFoundException("Permission not found for resource: " + resource + " and action: " + action));
            permissionsToAdd.add(permission);
        }

      // Assign only new permissions
       if (role.getPermissions().addAll(permissionsToAdd)) {
          roleRepository.save(role);
          System.out.println("Permissions " + permissionList + " assigned to role " + roleName);
       } else {
          System.out.println("Role " + roleName + " already has permissions " + permissionList);
       }
   }
}




