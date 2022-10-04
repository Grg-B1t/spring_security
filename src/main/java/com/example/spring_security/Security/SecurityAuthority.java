package com.example.spring_security.Security;

import org.springframework.security.core.GrantedAuthority;

import com.example.spring_security.Entity.Authority;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority{
    
    private final Authority outh;
    
    @Override
    public String getAuthority() {
        return outh.getName();
    }
    
}
