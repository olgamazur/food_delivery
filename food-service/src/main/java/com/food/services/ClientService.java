package com.food.services;


import com.food.dto.ClientDto;
import com.food.dto.ClientOrderDto;
import com.food.dto.RegisterClientDto;
import com.food.entities.ClientOrder;
import com.food.entities.Meal;
import com.food.entities.User;
import com.food.exсeptions.TargetAlreadyExistsException;
import com.food.repositories.ClientMealRepository;
import com.food.repositories.MealRepository;
import com.food.repositories.OrderRepository;
import com.food.repositories.UserRepository;
import com.food.utils.EntityConverter;
import com.food.exсeptions.TargetNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final EntityConverter entityConverter;
    private final ClientMealRepository clientMealRepository;


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


    public ClientOrderDto addMealToOrder(Long mealId, Long amount) {
        Long userId = getCurrentUserId();
        return mealRepository.findById(mealId)
                .map(meal -> {
                    return orderRepository.findClientOrderByUserId(userId)
                            .map(clientOrder -> {
                                Long newAmount=amount;
                                if (clientOrder.getMeals().containsKey(meal))
                                    newAmount=clientOrder.getMeals().get(meal)+amount;
                                clientOrder.getMeals().put(meal, newAmount);
                                clientOrder.setCreatedDate(LocalDateTime.now());
                                clientOrder.setTotalAmount(clientOrder.getTotalAmount() + meal.getPrice());
                                ClientOrder updatedOrder = orderRepository.save(clientOrder);
                                return entityConverter.transformOrder(updatedOrder);
                            })
                            .orElseGet(() ->
                            {
                                ClientOrder created = new ClientOrder();
                                created.setCreatedDate(LocalDateTime.now());
                                created.setUser(userRepository.findById(userId).orElseThrow(() -> new TargetNotFoundException(userId, "User")));
                                created.setTotalAmount(meal.getPrice()*amount);
                                Map<Meal, Long> meals = new HashMap<>();
                                meals.put(meal, amount);
                                created.setMeals(meals);
                                ClientOrder newOrder = orderRepository.save(created);
                                return entityConverter.transformOrder(newOrder);

                            });
                })
                .orElseThrow(() -> new TargetNotFoundException(mealId, "Meal"));


    }

    Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = obj.toString();

        User user = userRepository.findUserByName(username).orElseThrow(() -> new TargetNotFoundException(username, "user"));
        return user.getId();
    }
}
