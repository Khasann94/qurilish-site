package com.model.user;

import com.model.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

/**
 * Simple domain object that represents application user's role - ADMIN, USER, etc.
 */

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends Auditable<String> {
    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "role_permissions",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private Set<Permission> permissions = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "id: " + super.getId() + ", " +
                "name: " + name + ", " +
                "code: " + code + "}";
    }
}
