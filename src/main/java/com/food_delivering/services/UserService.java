package com.food_delivering.services;

import com.food_delivering.dto.ClientDto;
import com.food_delivering.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.food_delivering.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;
   private final EntityConverter entityConverter;

    public List<ClientDto> getClients() {
       return userRepository.findAll().stream().map(user -> entityConverter.transformClient(user)).collect(Collectors.toList());
    }
}
