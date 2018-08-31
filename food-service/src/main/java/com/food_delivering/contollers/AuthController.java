package com.food_delivering.contollers;

import com.food_delivering.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Configuration
public class AuthController {
    private final AuthService authService;


    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OAuth2AccessToken loginByUser(@RequestParam("username") String login, @RequestParam("password") String password) {
        return authService.getAccessTokenForLogin(login, password);
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OAuth2AccessToken register() {
        return authService.getAccessTokenForRegister();
    }


}
