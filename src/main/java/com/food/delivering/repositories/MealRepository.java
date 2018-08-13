package com.food.delivering.repositories;

import com.food.delivering.enteties.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
