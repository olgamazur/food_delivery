package com.food_delivering.contollers;

import com.food_delivering.dto.MealDto;
import com.food_delivering.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping(value = "/list")
    public List<MealDto> getMeals() {
        return mealService.getMeals();
    }
}
