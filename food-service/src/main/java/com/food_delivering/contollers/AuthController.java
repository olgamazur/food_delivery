package com.food_delivering.contollers;

import com.food_delivering.dto.ClientDto;
import com.food_delivering.dto.CreatedMealDto;
import com.food_delivering.dto.LoginClientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;

@RestController
public class AuthController {

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OAuth2AccessToken loginByUser(@RequestBody LoginClientDto loginClientDto) {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setClientId("food-delivery");
        resourceDetails.setClientSecret("food-delivery-secret");
        resourceDetails.setGrantType("password");
        resourceDetails.setPassword(loginClientDto.getPassword());
        resourceDetails.setUsername(loginClientDto.getUsername());
        resourceDetails.setScope(Arrays.asList("read", "write"));
        resourceDetails.setAccessTokenUri("http://localhost:8081/oauth/token");
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
        OAuth2AccessToken accessToken = template.getAccessToken();
        return accessToken;
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Object register() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setClientId("food-delivery");
        resourceDetails.setClientSecret("food-delivery-secret");
        resourceDetails.setGrantType("client_credentials");
        resourceDetails.setScope(Arrays.asList("read", "write"));
        resourceDetails.setAccessTokenUri("http://localhost:8081/oauth/token");
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
        OAuth2AccessToken accessToken = template.getAccessToken();
        /*if(successs){
            //TODO save user to database here
        }else {
            //todo error;
        }*/
        return accessToken;
    }
}
