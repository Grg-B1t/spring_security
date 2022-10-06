package com.example.jwt_basic.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    


    @Bean
    public UserDetailsService userDetailService() {
        var uds = new InMemoryUserDetailsManager();
        var u1 = User.withUsername("user1")
            .password(passwordEncoder().encode("123"))
            .authorities("read")
            .build();

        var u2 = User.withUsername("user2")
            .password(passwordEncoder().encode("456"))
            .authorities("read", "write", "delete")
            .build();

        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
        
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception{
        return httpSec.httpBasic()
            .and()
            .authorizeRequests()
            .mvcMatchers("/v/*", "/c/*")
            .authenticated()
            .anyRequest()
            .permitAll()
            .and()
            .csrf().disable()
            .build();
    }
}
