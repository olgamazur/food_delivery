package com.food_delivering.utils;


import com.food_delivering.dto.ClientOrderDto;
import com.food_delivering.dto.MealDto;
import com.food_delivering.entities.ClientOrder;
import com.food_delivering.entities.Meal;
import com.food_delivering.entities.User;
import com.food_delivering.repositories.UserRepository;
import com.food_delivering.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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
        MealDto dto = new MealDto(meal.getId(), meal.getName(), meal.getType(), meal.getCost());
        return dto;
    }

    public ClientOrderDto transformOrder(ClientOrder clientOrder) {
        List<MealDto> meals=new ArrayList<>();
        if (!CollectionUtils.isEmpty(clientOrder.getMeals())) {
            meals = clientOrder.getMeals()
                    .stream()
                    .map(this::transformMeal)
                    .collect(Collectors.toList());
        }
        ClientOrderDto dto = new ClientOrderDto(clientOrder.getId(), clientOrder.getCreatedDate(), clientOrder.getCost(), meals, clientOrder.getUser().getId());
        return dto;
    }
}
