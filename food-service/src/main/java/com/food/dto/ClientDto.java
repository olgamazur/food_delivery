package com.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDto {
    private String name;
    private Long id;
    private String password;
}
