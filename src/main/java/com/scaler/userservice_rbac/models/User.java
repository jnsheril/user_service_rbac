package com.scaler.userservice_rbac.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")

public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private Set<Role> role = new HashSet<>();



}
