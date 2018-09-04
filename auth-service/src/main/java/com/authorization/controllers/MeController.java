package com.authorization.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MeController {
    @RequestMapping(value = "/me")
    public Principal registerUser(Principal principal) {
        return principal;
    }
}
