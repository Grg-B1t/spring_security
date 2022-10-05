package com.example.jwt_basic.Configuration.Security.Manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.jwt_basic.Configuration.Security.Provider.ApiKeyProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomeAuthenticationManager implements AuthenticationManager {
    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var provider = new ApiKeyProvider(key);
        
        if(provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }

        return authentication;
    }

}
