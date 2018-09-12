package com.food_delivering.controllers;

import com.food_delivering.dto.RegisterClientDto;
import com.food_delivering.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Configuration
public class AuthController {
    private final ClientService clientService;


    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OAuth2AccessToken loginByUser(@RequestParam("username") String login, @RequestParam("password") String password) {

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(login, password);
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        ResourceOwnerPasswordResourceDetails resourceDetails = ownerPasswordResourceDetails();
        resourceDetails.setPassword(password);
        resourceDetails.setUsername(login);
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails,
                new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));

        OAuth2AccessToken accessToken = template.getAccessToken();
        sc.setAuthentication(null);
        return accessToken;
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OAuth2AccessToken register(@RequestParam("username") String login, @RequestParam("password") String password) {
        OAuth2RestTemplate template = new OAuth2RestTemplate(clientCredentialsResourceDetails());
        OAuth2AccessToken accessToken = template.getAccessToken();
        RegisterClientDto registerClientDto = new RegisterClientDto();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        registerClientDto.setPassword(passwordEncoder.encode(password));
        registerClientDto.setUsername(login);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", accessToken.getValue());
        HttpEntity<RegisterClientDto> request = new HttpEntity<>(registerClientDto, headers);
        ResponseEntity<RegisterClientDto> response;

        RestTemplate authtemplate = new RestTemplate();
        response = authtemplate.postForEntity("http://localhost:8081/auth/register", request, RegisterClientDto.class);
        clientService.addNewClient(registerClientDto);
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
