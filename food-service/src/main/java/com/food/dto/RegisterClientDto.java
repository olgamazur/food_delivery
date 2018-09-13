package com.food.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterClientDto {
    private String username;
    private String password;
}
