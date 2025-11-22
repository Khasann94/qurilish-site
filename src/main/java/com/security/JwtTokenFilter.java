package com.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT token filter that handles all HTTP requests to application.
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {


    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService userDetailsService;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, JwtUserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = getAccessToken(request);
        if (token != null) {
            jwtTokenProvider.validateToken(token, request);
            setAuthenticationContext(token, request);
        }
        filterChain.doFilter(request, response);

    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) return null;
        return header.split(" ")[1].trim();
    }


    public void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String token) {
        return userDetailsService.loadUserByUsername(jwtTokenProvider.getSubject(token));
    }


}
