package com.service.user;

import com.model.user.User;
import com.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.model.Status.ACTIVE;


/**
 * Implementation of {@link UserService} interface.
 * Wrapper for {@link UserRepository} + business logic.
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder;


    public void save(User user) {
        userRepository.save(user);
    }


    public User authenticate(String username) {
        return userRepository.findByUsername(username);
    }



    private boolean passwordMatches(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
//        return true;
    }

    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username.toLowerCase());
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }



    public List<String> findRoleNames(Long userId) {
        return userRepository.findRoleNames(userId);
    }


    public List<Long> findRoleIds(Long userId) {
        return userRepository.findRoleIds(userId);
    }

}
