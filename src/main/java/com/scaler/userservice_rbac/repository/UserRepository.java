package com.scaler.userservice_rbac.repository;


import com.scaler.userservice_rbac.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail (String email);
}
