package com.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisteredUserDetailsDto {
    private String username;

    private String password;
}
