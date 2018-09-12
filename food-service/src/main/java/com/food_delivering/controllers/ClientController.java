package com.food_delivering.controllers;

import com.food_delivering.dto.ClientOrderDto;
import com.food_delivering.dto.MealDto;
import com.food_delivering.services.ClientService;
import com.food_delivering.services.MealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final MealService mealService;

    @GetMapping(value = "/meal/list")
    public List<MealDto> getMeals() {
        return mealService.getMeals();
    }

    @PutMapping(value = "/{mealId}/amount/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientOrderDto addMealToOrder(@PathVariable Long mealId,@PathVariable Long amount) {
        return clientService.addMealToOrder(mealId,amount);
    }
}
