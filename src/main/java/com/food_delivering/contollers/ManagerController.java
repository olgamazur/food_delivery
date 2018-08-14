package com.food_delivering.contollers;

import com.food_delivering.dto.ClientDto;
import com.food_delivering.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final UserService userService;

    @GetMapping(value = "/list")
    public List<ClientDto> getClients() {
        return userService.getClients();
    }

}
