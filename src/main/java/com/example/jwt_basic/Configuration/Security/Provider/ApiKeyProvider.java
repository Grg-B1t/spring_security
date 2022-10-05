package com.example.jwt_basic.Configuration.Security.Provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.jwt_basic.Configuration.Security.Authentication.ApiKeyAuthentication;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider{
    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        ApiKeyAuthentication auth = (ApiKeyAuthentication) authentication;

        if(key.equals(auth.getKey())) {
            auth.setAuthenticated(true);
            return auth;
        }
        
        throw new BadCredentialsException(key + " is Invalid!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
