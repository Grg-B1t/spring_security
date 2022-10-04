package com.example.spring_security.Configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class securityConfig {
    
    // @Bean
    // public UserDetailsService userDetails() {
    //     InMemoryUserDetailsManager userMgr = new InMemoryUserDetailsManager();

    //     var u1 = User.withUsername("Sam").password("123").authorities("read").build();

    //     userMgr.createUser(u1);

    //     return userMgr;
    // }

    @Bean
    public PasswordEncoder passDetail(){

        return NoOpPasswordEncoder.getInstance();
    }
}
