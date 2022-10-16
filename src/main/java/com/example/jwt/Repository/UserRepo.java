package com.example.jwt.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.Entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, String> {

}
