package com.scaler.userservice_rbac.repository;

import com.scaler.userservice_rbac.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(@Param("name") String name);  // Should match your method call

    boolean existsByName(String roleName);

}

