package com.example.jwt_basic.Security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Condition {
    
    public Boolean condition() {
        var k = SecurityContextHolder.getContext().getAuthentication();

        return false;
    }
}
