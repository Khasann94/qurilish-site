package com.repository.user;

import com.model.user.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Permission findByName(String name);

    @Query(value = "SELECT p FROM Permission p WHERE p.id NOT IN (SELECT p.id FROM Permission p LEFT JOIN p.roles r WHERE r.id = ?1)")
    List<Permission> findNotInRole(Long roleId);

    List<Permission> findAllByRolesId(Long roleId);


    @Query(value = "SELECT p FROM Permission p WHERE p.id IN (?1)")
    List<Permission> findAllByPermissionIds(List<Long> permissionIds);
}
