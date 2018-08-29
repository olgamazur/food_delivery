package com.authorization.dto;

import com.authorization.configs.OAuth2Config;
import lombok.Data;
@Data
public class UserAuthDto {
        private OAuth2Config.Authority role;
        private String username;
}
