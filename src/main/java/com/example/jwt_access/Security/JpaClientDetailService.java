package com.example.jwt_access.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import com.example.jwt_access.Repository.ClientRepo;

public class JpaClientDetailService implements ClientDetailsService{

    @Autowired
    ClientRepo cr;
    
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        
        return cr.findClientByClientId(clientId)
            .map(a -> new SecurityClient(a))
            .orElseThrow(() -> new ClientRegistrationException("User is not registered!"));
            
    }

}
