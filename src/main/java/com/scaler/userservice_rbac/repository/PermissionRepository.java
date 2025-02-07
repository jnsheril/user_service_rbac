package com.scaler.userservice_rbac.repository;

import com.scaler.userservice_rbac.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Optional<Permission> findByName(String name);



    boolean existsByName(String permissionName);
}
