package com.bank.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils utils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            Authentication authentication = utils.populateAuthenticationTokenFromJWT(jwt);

            // Add debug statement to log the roles
            System.out.println("JWT authorities: " + authentication.getAuthorities());
            System.out.println("User authenticated: " + authentication.isAuthenticated());
            System.out.println("User details: " + authentication.getPrincipal());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Saved auth token in sec ctx: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        }
        filterChain.doFilter(request, response);
    }
}
