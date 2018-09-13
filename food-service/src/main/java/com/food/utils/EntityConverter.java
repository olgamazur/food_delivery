package com.food.utils;


import com.food.dto.ClientOrderDto;
import com.food.dto.MealDto;
import com.food.entities.ClientOrder;
import com.food.entities.Meal;
import com.food.entities.User;
import com.food.repositories.UserRepository;
import com.food.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityConverter {
    private final UserRepository userRepository;

    public ClientDto transformClient(User user) {
        ClientDto dto = new ClientDto(user.getName(), user.getId(), user.getPassword());
        return dto;
    }

   public MealDto transformMeal(Meal meal) {
         MealDto dto = new MealDto(meal.getId(), meal.getName(), meal.getType(), meal.getPrice());
        return dto;
    }

    public ClientOrderDto transformOrder(ClientOrder clientOrder) {

        Map<MealDto, Long> meals=new HashMap<>();
        if (!CollectionUtils.isEmpty(clientOrder.getMeals())) {
           meals =
                    clientOrder.getMeals().entrySet().stream()
                            .collect(Collectors.toMap(
                                    e -> transformMeal(e.getKey()),
                                    e -> e.getValue()
                            ));
        }
        ClientOrderDto dto = new ClientOrderDto(clientOrder.getId(), clientOrder.getCreatedDate(), clientOrder.getTotalAmount(), meals, clientOrder.getUser().getId());

        return dto;
    }
}
