package com.example.jwt_basic.Configuration.Security.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt_basic.Configuration.Security.Authentication.ApiKeyAuthentication;
import com.example.jwt_basic.Configuration.Security.Manager.CustomeAuthenticationManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {
    private final String key;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CustomeAuthenticationManager ca = new CustomeAuthenticationManager(key);
        var requestKey = request.getHeader("key");

        if(requestKey == null || "null".equals(requestKey)){
            filterChain.doFilter(request, response);
        }

        var auth = new ApiKeyAuthentication(requestKey);

        try {
            var a = ca.authenticate(auth);
            if(a.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            }
            else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    
}
