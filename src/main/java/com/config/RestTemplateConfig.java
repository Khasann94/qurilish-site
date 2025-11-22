package com.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    private final HttpServletRequest request;

    public RestTemplateConfig(HttpServletRequest request) {
        this.request = request;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        ClientHttpRequestInterceptor interceptor = (httpRequest, body, execution) -> {
            // HttpServletRequest orqali request olamiz
            String bearerToken = this.request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                httpRequest.getHeaders().add("Authorization", bearerToken);
            }
            return execution.execute(httpRequest, body);
        };
        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        return restTemplate;
    }
}