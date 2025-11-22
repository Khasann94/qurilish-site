package com.controller.user;


import com.dto.user.AuthenticationRequestDto;
import com.exception.RecordNotFoundException;
import com.model.user.User;
import com.security.JwtTokenProvider;
import com.service.user.RoleService;
import com.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v.1/")
public class AuthenticationController {
    private final RoleService roleService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("auth-payload")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthenticationRequestDto requestDto, HttpServletRequest request) {
        User user;
        try {
            String username = requestDto.getUsername().toLowerCase();
            user = userService.authenticate(username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new RecordNotFoundException(e.getMessage());
        }
        if (user == null) throw new RecordNotFoundException("User does not exist!");
        List<String> roleNames = userService.findRoleNames(user.getId());
        List<String> permissionsDto = roleService.findPermissionNames(userService.findRoleIds(user.getId()));
        String token = jwtTokenProvider.createToken(user.getUsername(), roleNames, permissionsDto, request);
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("token", token);
        response.put("username", user.getUsername());
        response.put("role", roleNames);
        response.put("permissions", permissionsDto);
        return ResponseEntity.ok(response);
    }



}
