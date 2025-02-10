package com.scaler.userservice_rbac.repository;

import com.scaler.userservice_rbac.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    // Find permission by resource and action
    Optional<Permission> findByResourceAndAction(String resource, String action);

    // Check if a permission exists by resource and action
    boolean existsByResourceAndAction(String resource, String action);
}
