package com.example.jwt.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.Entity.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, String>{

}
