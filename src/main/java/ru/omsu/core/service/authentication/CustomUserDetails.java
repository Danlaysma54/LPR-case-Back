package ru.omsu.core.service.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final String userId;  // Добавляем userId

    public CustomUserDetails(String username, String password, String userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    // Геттер для userId
    public String getUserId() {
        return userId;
    }

    // Стандартные методы UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();  // или ваши роли, если есть
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}