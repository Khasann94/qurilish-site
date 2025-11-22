package com.repository.user;

import com.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT r.name FROM User u LEFT JOIN u.roles r WHERE u.id = ?1 ORDER BY r.name")
    List<String> findRoleNames(Long userId);

    @Query("SELECT r.id FROM User u LEFT JOIN u.roles r WHERE u.id = ?1 ORDER BY r.name")
    List<Long> findRoleIds(Long userId);
}
