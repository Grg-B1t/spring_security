package com.example.jwt_basic.Configuration.Security.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt_basic.Configuration.Security.Authentication.CustomAuthentication;
import com.example.jwt_basic.Configuration.Security.Manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager cas;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            String key = String.valueOf(request.getHeader("sam"));
            CustomAuthentication ca = new CustomAuthentication(false, key);
            var v = cas.authenticate(ca);

            if(v.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(v);
                filterChain.doFilter(request, response);
            }
    }
    
}
