package com.authorization.entities;

import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;
    @Data
    @Entity
    @Table(name = "auth")
    public class UserDetails {

        @Id
        @Column(name = "username", unique = true)
        private String username;

        @Column(name = "password")
        private String password;

        @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
        @Column(name = "created_date")
        private LocalDateTime createdDate;
}
