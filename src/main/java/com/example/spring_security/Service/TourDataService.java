package com.example.spring_security.Service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring_security.Repository.Repo;
import com.example.spring_security.Security.SecurityUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TourDataService implements UserDetailsService {
    

    private final Repo rp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u1 = rp.findUserByUsername(username);
        return u1.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException(username + " is not found!"));
    }
  
}
