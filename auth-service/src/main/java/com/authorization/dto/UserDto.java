package com.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;

    private String password;

    private LocalDateTime createdDate;
}
