package com.scaler.userservice_rbac.intiazlizer;

import com.scaler.userservice_rbac.models.Permission;
import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.repository.PermissionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
@Transactional
public class PermissionIntializer {
    private final PermissionRepository permissionRepository;

    public PermissionIntializer(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    @PostConstruct
    public void initRoles() {
        System.out.println("Initializing permission...");

        List<String> permissions = List.of("read", "write", "view","manage","delete","add");

        for (String permissionName : permissions) {
            if (!permissionRepository.existsByName(permissionName)) { // Prevent duplicate entries
                Permission permission = new Permission(permissionName);
                permissionRepository.save(permission);
                System.out.println("Inserted permission: " + permissionName);
            }
        }
    }


}
