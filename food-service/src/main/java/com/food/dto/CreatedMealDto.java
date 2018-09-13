package com.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatedMealDto {
    private String name;
    private String type;
    private Long cost;
}
