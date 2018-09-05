package com.food_delivering.services;


import com.food_delivering.dto.RegisterClientDto;
import com.food_delivering.entities.Meal;
import com.food_delivering.entities.User;
import com.food_delivering.exсeptions.TargetAlreadyExistsException;
import com.food_delivering.exсeptions.TargetNotFoundException;
import com.food_delivering.repositories.UserRepository;
import com.food_delivering.dto.ClientDto;
import com.food_delivering.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final UserRepository userRepository;
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
}
