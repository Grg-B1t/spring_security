package com.example.jwt_access.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt_access.Entity.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer>{
    
    Optional<Client> findClientByClientId(String clientId);
}
