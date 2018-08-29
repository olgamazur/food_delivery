package com.authorization.controllers;

import com.authorization.dto.UserDto;
import com.authorization.dto.UserRegisterDto;
import com.authorization.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserOperationsController {
    private final AuthService authService;

    @GetMapping(value = "/hi")
    public String Hello() {
        return "hi";

    }
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        log.info("Register via mobile client with user: {}", userRegisterDto);
        UserDto resultDto = authService.register(userRegisterDto);
        return resultDto;
    }
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterDto loginByUser(@RequestBody UserRegisterDto userToLogin) {
        return authService.login(userToLogin);
    }
}
