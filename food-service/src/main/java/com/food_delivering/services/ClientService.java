package com.food_delivering.services;


import com.food_delivering.dto.ClientOrderDto;
import com.food_delivering.dto.MealDto;
import com.food_delivering.dto.RegisterClientDto;
import com.food_delivering.entities.ClientOrder;
import com.food_delivering.entities.Meal;
import com.food_delivering.entities.User;
import com.food_delivering.exсeptions.TargetAlreadyExistsException;
import com.food_delivering.exсeptions.TargetNotFoundException;
import com.food_delivering.repositories.MealRepository;
import com.food_delivering.repositories.OrderRepository;
import com.food_delivering.repositories.UserRepository;
import com.food_delivering.dto.ClientDto;
import com.food_delivering.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.el.ELException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final EntityConverter entityConverter;

    public List<ClientDto> getClients() {
        return userRepository.findAll().stream().map(user -> entityConverter.transformClient(user)).collect(Collectors.toList());
    }

    public ClientDto getClient(Long userId) {
        return userRepository.findById(userId)
                .map(user -> entityConverter.transformClient(user))
                .orElseThrow(() -> new TargetNotFoundException(userId, "Client"));
    }

    public ClientDto addNewClient(RegisterClientDto registerClientDto) {
        String username = registerClientDto.getUsername();
        if (userRepository.existsUserByName(username)) {
            throw new TargetAlreadyExistsException(username, "user");
        }
        User created = new User();
        created.setName(registerClientDto.getUsername());
        created.setPassword(registerClientDto.getPassword());

        User newUser = userRepository.save(created);
        return entityConverter.transformClient(newUser);
    }


    public ClientOrderDto addMealToOrder(Long mealId, Long userId) {

        return mealRepository.findById(mealId)
                .map(meal -> {
                    return orderRepository.findClientOrderByUserId(userId)
                            .map(clientOrder -> {
                                clientOrder.getMeals().add(meal);
                                clientOrder.setCreatedDate(LocalDateTime.now());
                                clientOrder.setCost(clientOrder.getCost() + meal.getCost());
                                ClientOrder updatedOrder = orderRepository.save(clientOrder);
                                return entityConverter.transformOrder(updatedOrder);
                            })
                            .orElseGet(() ->
                            {
                                ClientOrder created = new ClientOrder();
                                created.setCreatedDate(LocalDateTime.now());
                                created.setUser(userRepository.findById(userId).orElseThrow(() -> new TargetNotFoundException(userId, "User")));
                                created.setCost(meal.getCost());
                                List<Meal> meals = new ArrayList<>();
                                meals.add(meal);
                                created.setMeals(meals);
                                ClientOrder newOrder = orderRepository.save(created);
                                return entityConverter.transformOrder(newOrder);

                            } );
                })
                .orElseThrow(() -> new TargetNotFoundException(mealId, "Meal"));


    }
}
