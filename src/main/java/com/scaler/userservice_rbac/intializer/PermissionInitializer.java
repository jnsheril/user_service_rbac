package com.scaler.userservice_rbac.intializer;

import com.scaler.userservice_rbac.models.Permission;
import com.scaler.userservice_rbac.repository.PermissionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class PermissionInitializer {
    private final PermissionRepository permissionRepository;

    public PermissionInitializer(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PostConstruct
    public void init() {
        if (permissionRepository.count() == 0) {
            permissionRepository.saveAll(List.of(
                    new Permission("USER", "READ"),
                    new Permission("USER", "WRITE"),
                    new Permission("USER", "DELETE"),
                    new Permission("ORDER", "READ"),
                    new Permission("ORDER", "WRITE"),
                    new Permission("ORDER", "DELETE")
            ));
        }
    }
}




