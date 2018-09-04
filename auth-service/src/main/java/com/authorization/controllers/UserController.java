package com.authorization.controllers;

import com.authorization.dto.UserDto;
import com.authorization.dto.UserRegisterDto;
import com.authorization.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final AuthService authService;
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {

        UserDto resultDto = authService.register(userRegisterDto);
        return resultDto;
    }
}
