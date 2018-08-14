package com.food_delivering.services;

import com.food_delivering.dto.MealDto;
import com.food_delivering.repositories.MealRepository;
import com.food_delivering.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealService {
   private final MealRepository mealRepository;
   private final EntityConverter entityConverter;

    public List<MealDto> getMeals() {
        return mealRepository.findAll().stream().map(meal -> entityConverter.transformMeal(meal)).collect(Collectors.toList());
    }
}
