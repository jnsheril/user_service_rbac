package com.scaler.userservice_rbac.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseModel{
    private String role;
    @ManyToMany
    private Set<Permission> permissions = new HashSet<>();
}
