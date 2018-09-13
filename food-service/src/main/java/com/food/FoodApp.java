package com.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class FoodApp {
    public static void main(String[] args) {
        SpringApplication.run(FoodApp.class, args);
    }
}
