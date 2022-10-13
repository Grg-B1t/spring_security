package com.example.jwt_access.Security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.example.jwt_access.Entity.Client;



public class SecurityClient implements ClientDetails{

    private Client client;

    public SecurityClient(Client client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
    
        return client.getSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return Set.of(client.getScope());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {  
        return Set.of(client.getGrantType());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return List.of(() -> client.getScope());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        // TODO Auto-generated method stub
        return null;
    }

}
