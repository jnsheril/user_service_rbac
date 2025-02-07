package com.scaler.userservice_rbac.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "role")

public class Role extends BaseModel {

    private String name;
    @ManyToMany
    private Set<Permission> permissions = new HashSet<>();

    // Constructor should set the name field properly
    public Role(String name) {
        this.name = name;  // Set the name here
    }
    // No-argument constructor (required by JPA)
    public Role() {
    }

}
