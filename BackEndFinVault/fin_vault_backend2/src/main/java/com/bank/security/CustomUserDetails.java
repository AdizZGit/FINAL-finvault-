package com.bank.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.model.User;

public class CustomUserDetails implements UserDetails {
    private User user;
    
    public CustomUserDetails(User entity) {
        this.user = entity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return roles with the 'ROLE_' prefix
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public User getUser() {
        return user;
    }
}
