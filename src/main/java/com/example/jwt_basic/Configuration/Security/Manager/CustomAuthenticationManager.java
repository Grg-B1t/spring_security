package com.example.jwt_basic.Configuration.Security.Manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.jwt_basic.Configuration.Security.Provider.CustomAuthenticationProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomAuthenticationProvider pr;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(pr.supports(authentication.getClass())){
            return pr.authenticate(authentication);
        }
        throw new BadCredentialsException("Bad Credential Exception");
    }
    
}
