package com.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableTransactionManagement
public class JpaAuditingConfiguration {
    @Bean
    @Qualifier("auditorProvider")
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Optional<String> username;
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                username = Optional.of("admin");
            } else
                username = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
            return username;
        };
    }
}
