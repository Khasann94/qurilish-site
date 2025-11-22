package com.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.
                        addMapping("/**")
                        .allowedMethods(org.springframework.web.cors.CorsConfiguration.ALL)
                        .allowedHeaders(org.springframework.web.cors.CorsConfiguration.ALL)
                        .allowedOriginPatterns(org.springframework.web.cors.CorsConfiguration.ALL);;
            }
        };
    }
}
