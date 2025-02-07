package com.scaler.userservice_rbac.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        this.name = name;  // Set the name here
    }
    public Role() {
    }

}
