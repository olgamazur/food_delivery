package com.food_delivering.utils;


import com.food_delivering.dto.MealDto;
import com.food_delivering.entities.Meal;
import com.food_delivering.entities.User;
import com.food_delivering.repositories.UserRepository;
import com.food_delivering.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityConverter {
    private final UserRepository userRepository;

    public ClientDto transformClient(User user) {
        ClientDto dto = new ClientDto(user.getName(), user.getId(),user.getPassword());
        return dto;
    }

    public MealDto transformMeal(Meal meal) {
        MealDto dto = new MealDto(meal.getId(), meal.getName(), meal.getType(), meal.getCost());
        return dto;
    }
}
