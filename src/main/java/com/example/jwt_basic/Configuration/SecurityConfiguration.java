package com.example.jwt_basic.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.httpBasic()
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .build();
    }

    @Bean
    public UserDetailsService userDetails() {
      UserDetails u1 = User.withUsername("user1")
                    .password(passwordEncoder().encode("123"))
                    .authorities("read", "write")
                    .build();

    UserDetails u2 = User.withUsername("user2")
                    .password(passwordEncoder().encode("456"))
                    .authorities("read", "write")
                    .build();

        var uds = new InMemoryUserDetailsManager();

        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
}
