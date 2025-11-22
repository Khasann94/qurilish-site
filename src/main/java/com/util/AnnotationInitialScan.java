package com.util;

import com.model.user.Role;
import com.model.user.User;
import com.service.user.RoleService;
import com.service.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.model.Status.ACTIVE;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AnnotationInitialScan {
    private final RoleService roleService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        this.startRole();
        this.startUser();
    }


    public void startRole() {
        Role role = roleService.findByCode("ADMIN_CODE");
        if (role == null) {
            role = new Role();
            role.setName("ADMIN");
            role.setCode("ADMIN_CODE");
            roleService.save(role);
        }
        roleService.save(role);
    }

    public void startUser() {
        User user = userService.findByUsername("admin");
        if (user == null) {
            user = new User();
            user.setUsername("admin");
            user.setStatus(ACTIVE);
            user.setPassword(passwordEncoder.encode("p@ssw0rd2025"));
            user.getRoles().add(roleService.findByCode("ADMIN_CODE"));
            userService.save(user);
        }
    }


}
