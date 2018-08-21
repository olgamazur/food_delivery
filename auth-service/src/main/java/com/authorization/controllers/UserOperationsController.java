package com.authorization.controllers;

import com.authorization.dto.RegisteredUserDetailsDto;
import com.authorization.dto.UserDetailsDto;
import com.authorization.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
 /*    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
  public UserDetailsDto registerUser(@RequestBody @Valid RegisteredUserDetailsDto userRegisterDto) {
        UserDetailsDto resultDto = authService.register(userRegisterDto);
        return resultDto;
    }*/

}
