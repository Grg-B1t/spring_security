package com.example.jwt.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt.Entity.JwtRequest;
import com.example.jwt.Entity.JwtResponse;
import com.example.jwt.Entity.User;
import com.example.jwt.Repository.UserRepo;
import com.example.jwt.Utils.Utils;

@Service
public class JwtService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private AuthenticationManager am;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String username = jwtRequest.getUsername();
		String password = jwtRequest.getPassword();
		
		authenticate(username, password);
		
		UserDetails ud = loadUserByUsername(username);
		String newGeneratedToken = utils.generateToken(ud);
		User user = userRepo.findById(username).get();
		
		return new JwtResponse(user, newGeneratedToken);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepo.findById(username).get();
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(
						user.getUsername(), 
						user.getPassword(),
						getAuthority(user)
					);
		} else {
			throw new UsernameNotFoundException("User not found with the username: " + username);
		}
	}

	public Set getAuthority(User user) {
		Set<SimpleGrantedAuthority> authority = new HashSet<>();
		user.getRole().forEach(role -> {
				authority.add(new SimpleGrantedAuthority("Role: " + role.getRoleName()));
		});
		
		return authority;
	}
	
	public void authenticate(String username, String password) throws Exception{
		try {
			am.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException de) {
			throw new Exception("User diabled: ", de);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid credentials: ", e);
		}
	}

}
