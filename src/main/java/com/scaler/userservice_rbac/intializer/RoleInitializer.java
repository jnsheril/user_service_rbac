package com.scaler.userservice_rbac.intializer;

import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
@Transactional
public class RoleInitializer {
    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @PostConstruct
    public void initRoles() {
        // set logger
        System.out.println("Initializing roles...");
        List<String> roles = List.of("ADMIN", "STAFF", "SUPERVISOR");
        for (String roleName : roles) {
            if (!roleRepository.existsByName(roleName)) { // Prevent duplicate entries
                Role role = new Role(roleName);
                roleRepository.save(role);
                System.out.println("Inserted role: " + roleName);
            }
        }
    }
}
