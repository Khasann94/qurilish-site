package com.repository.user;

import com.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 */

public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, JpaRepository<Role, Long>, QueryByExampleExecutor<Role> {

    Role findByCode(String code);


    @Query("SELECT p.name FROM Role r LEFT JOIN r.permissions p WHERE r.id IN(?1) ORDER BY p.name")
    List<String> findPermissionNames(List<Long> roleIds);

}
