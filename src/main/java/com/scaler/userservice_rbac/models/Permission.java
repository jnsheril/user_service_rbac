package com.scaler.userservice_rbac.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission extends BaseModel{

    private String name; // A descriptive name for the permission, like "view_dashboard" or "edit_user"

    private String resource; // The resource this permission applies to (e.g., "dashboard", "user", "order")

    private String action; //  The action associated with this resource (e.g., "read", "write", "delete")

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();
}
