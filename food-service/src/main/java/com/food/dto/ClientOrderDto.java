package com.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ClientOrderDto {
    private Long id;
    private LocalDateTime createdDate;
    private Long total;
    private Map<MealDto,Long> meals;
    private Long userId;
}
