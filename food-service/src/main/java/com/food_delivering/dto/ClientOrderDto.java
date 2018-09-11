package com.food_delivering.dto;

import com.food_delivering.entities.Meal;
import com.food_delivering.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ClientOrderDto {
    private Long id;
    private LocalDateTime createdDate;
    private Long cost;
    private List<MealDto> meals;
    private Long userId;
}
