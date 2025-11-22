package com.model.user;

import com.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "permissions")
public class Permission extends Auditable<String> {
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Permission{" +
                "id= " + super.getId() + ", " +
                "name= " + name + ", " +
                "type= " + type + "}";
    }
}
