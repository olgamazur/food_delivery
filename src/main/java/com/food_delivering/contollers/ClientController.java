package com.food_delivering.contollers;

import com.food_delivering.dto.ClientDto;
import com.food_delivering.dto.MealDto;
import com.food_delivering.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.food_delivering.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final UserService userService;
    private final MealService mealService;

    @GetMapping(value = "/meal/list")
    public List<MealDto> getMeals() {
        return mealService.getMeals();
    }


}
