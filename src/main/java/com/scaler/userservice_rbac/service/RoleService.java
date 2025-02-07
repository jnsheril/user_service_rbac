package com.scaler.userservice_rbac.service;

import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}


