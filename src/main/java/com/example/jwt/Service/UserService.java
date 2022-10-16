package com.example.jwt.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.Entity.Role;
import com.example.jwt.Entity.User;
import com.example.jwt.Repository.RoleRepo;
import com.example.jwt.Repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	public void initRoleAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("admin");
		adminRole.setRoleDesc("Admin role");
		
		roleRepo.save(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDesc("Default role for newly created record");
		
		roleRepo.save(userRole);
		
		User adminUser = new User();
		adminUser.setUsername("admin");
		adminUser.setPassword(getEncodedPassword("admin123"));
		adminUser.setUserFirstName("Sam");
		adminUser.setUserLastName("Lee");
		Set<Role> adminRoles = new HashSet<>(); 
		
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
	}
	
	public User registerNewUser(User user) {
		Role role = roleRepo.findById("User").get();
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRole(userRoles);
		user.setPassword(getEncodedPassword(user.getPassword()));
		
		return userRepo.save(user);
	}

	private String getEncodedPassword(String password) {
		return passEncoder.encode(password);
	}
}
