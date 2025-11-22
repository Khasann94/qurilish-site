package com.service.user;

import com.model.user.Role;
import com.repository.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
@Validated
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    public void save(Role role) {
        roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }


    public Role findByCode(String code) {
        Role role = roleRepository.findByCode(code);
        if (role == null) {
            log.warn("IN findByCode - no role found by code: {}", code);
            return null;
        }
        log.info("IN findByCode - role: {} found by code: {}", role, code);
        return role;
    }

    public List<String> findPermissionNames(List<Long> roleIds) {
        return roleRepository.findPermissionNames(roleIds);
    }

    public List<Role> findAllByIds(List<Long> roleIds) {
        return roleRepository.findAllById(roleIds);
    }

}
