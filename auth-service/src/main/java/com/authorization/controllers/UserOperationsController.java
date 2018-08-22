package com.authorization.controllers;

import com.authorization.dto.RegisteredUserDetailsDto;
import com.authorization.dto.UserDetailsDto;
import com.authorization.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserOperationsController {
    private final AuthService authService;

    @GetMapping(value = "/hi")
    public String Hello() {
        return "hi";

    }
    @GetMapping(value = "/login")
    public String Login() {
        return "login";

    }

}
