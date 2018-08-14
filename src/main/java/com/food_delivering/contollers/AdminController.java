package com.food_delivering.contollers;

import com.food_delivering.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
    @Validated
    @RestController
    @RequestMapping("/admin")
    @RequiredArgsConstructor
    public class AdminController {
        private final UserService userService;
}
