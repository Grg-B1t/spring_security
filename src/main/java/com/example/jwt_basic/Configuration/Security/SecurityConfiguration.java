package com.example.jwt_basic.Configuration.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.jwt_basic.Configuration.Security.Filter.ApiKeyFilter;

@Configuration
public class SecurityConfiguration {

    @Value("${secret}")
    private String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.httpBasic()
            .and()
            .addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class)
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .build();
    }
}
