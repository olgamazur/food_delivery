package com.food_delivering.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    public OAuth2AccessToken getAccessTokenForLogin(String username, String password) {


        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        ResourceOwnerPasswordResourceDetails resourceDetails = ownerPasswordResourceDetails();
        resourceDetails.setPassword(password);
        resourceDetails.setUsername(username);
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails,
                new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));

        OAuth2AccessToken accessToken = template.getAccessToken();
        sc.setAuthentication(null);
        return accessToken;
    }

    public OAuth2AccessToken getAccessTokenForRegister() {
        OAuth2RestTemplate template = new OAuth2RestTemplate(clientCredentialsResourceDetails());
        OAuth2AccessToken accessToken = template.getAccessToken();

        return accessToken;
    }

    private AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return new Authentication() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public Object getCredentials() {
                    return authentication.getCredentials();
                }

                @Override
                public Object getDetails() {
                    return authentication.getDetails();
                }

                @Override
                public Object getPrincipal() {
                    return authentication.getPrincipal();
                }

                @Override
                public boolean isAuthenticated() {
                    return true;
                }

                @Override
                public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

                }

                @Override
                public String getName() {
                    return "aaaaaa";
                }
            };
        }
    };

    @Bean
    @Scope(scopeName = "prototype")
    @ConfigurationProperties("security.oauth2.client")
    public ResourceOwnerPasswordResourceDetails ownerPasswordResourceDetails() {
        return new ResourceOwnerPasswordResourceDetails();
    }

    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }
}