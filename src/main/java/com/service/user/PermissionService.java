package com.service.user;

import com.model.user.Permission;
import com.repository.user.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;


    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

    public Permission getByPermissionId(Long id) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission == null) {
            log.warn("IN findById - no permission found by id: {}", id);
            return null;
        }

        log.info("IN findById - permission: {} found by id: {}", permission, id);
        return permission;
    }

    public Permission getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }

    public List<Permission> findNotInRole(Long roleId) {
        return permissionRepository.findNotInRole(roleId);
    }
}
