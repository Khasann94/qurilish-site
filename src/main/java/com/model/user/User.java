package com.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Auditable;
import com.model.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

/**
 * Simple domain object that represents application user.
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable<String> {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    public void setUsername(String username) {
        this.username = username.trim();
    }
}
