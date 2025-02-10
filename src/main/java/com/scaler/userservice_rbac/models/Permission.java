package com.scaler.userservice_rbac.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Permission extends BaseModel {
    private String resource; // e.g., "USER", "ORDER", "PRODUCT"
    private String action;   // e.g., "READ", "WRITE", "DELETE"

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public Permission(String resource, String action) {
        this.resource = resource;
        this.action = action;
    }

    public Permission() {

    }

    public String getAuthority() {
        return this.resource + "_" + this.action; // e.g., "USER_READ"
    }
}
