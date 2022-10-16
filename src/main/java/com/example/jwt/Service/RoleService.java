package com.example.jwt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.Entity.Role;
import com.example.jwt.Repository.RoleRepo;

@Service
public class RoleService {
	@Autowired
	private RoleRepo roleRepo;
	
	public Role createRole(Role role) {
		return roleRepo.save(role);
	}
}
