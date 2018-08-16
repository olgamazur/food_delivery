package com.food_delivering.services;



import com.food_delivering.exсeptions.TargetAlreadyExistsException;
import com.food_delivering.exсeptions.TargetNotFoundException;
import com.food_delivering.dto.CreatedMealDto;
import com.food_delivering.dto.MealDto;
import com.food_delivering.entities.Meal;
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
    public MealDto getMeal(String name) {
        return mealRepository.findMealByName(name)
                .map(meal -> entityConverter.transformMeal(meal))
                .orElseThrow(() -> new TargetNotFoundException( name,"Meal"));
    }
    public MealDto addNewMeal(CreatedMealDto mealDto) {
        log.info("Create meal by DTO data {}", mealDto);
        String mealName = mealDto.getName();
        if (mealRepository.existsMealByName(mealName)) {
            log.warn("Meal already exists {}", mealName);
            throw new TargetAlreadyExistsException(mealName,"meal");
        }
        Meal created=new Meal();
        created.setName(mealDto.getName());
        created.setType(mealDto.getType());
        created.setCost(mealDto.getCost());

        Meal newMeal = mealRepository.save(created);
        log.info("New meal {} was successfully created", newMeal);
        return entityConverter.transformMeal(newMeal);
    }

    public MealDto deleteMeal(String name) {
        log.info("Delete meal with name {}", name);
        return mealRepository.findMealByName(name)
                .map(meal -> {
                    mealRepository.deleteById(meal.getId());
                    log.info("Meal with name {} was successfully removed from database", name);
                    return entityConverter.transformMeal(meal);
                })
                .orElseThrow(() -> new TargetNotFoundException(name,"Meal"));
    }

    public List<MealDto> getDrinks() {
        return mealRepository.findAllDrinks().stream().map(meal -> entityConverter.transformMeal(meal)).collect(Collectors.toList());
    }
    public List<MealDto> getFood() {
        return mealRepository.findAllFood().stream().map(meal -> entityConverter.transformMeal(meal)).collect(Collectors.toList());
    }
}
