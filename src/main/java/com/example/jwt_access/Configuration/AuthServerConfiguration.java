package com.example.jwt_access.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.example.jwt_access.Security.JpaClientDetailService;

import ch.qos.logback.core.net.server.Client;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter{
    
    @Autowired
    private AuthenticationManager am;

   @Override
    public void configure(ClientDetailsServiceConfigurer cli) throws Exception{

      cli.withClientDetails(cds());

    }


    @Bean
    public TokenStore tStore() {
        return new JwtTokenStore(converter());
    }

    @Bean
    public JwtAccessTokenConverter converter() {
        var conv = new JwtAccessTokenConverter();

        conv.setSigningKey("secret");
        return conv;
    } 

    @Bean
    public JpaClientDetailService cds() {
        return new JpaClientDetailService();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoint) throws Exception{
        endpoint.authenticationManager(am)
            .tokenStore(tStore())
            .accessTokenConverter(converter());
    }

    
}
