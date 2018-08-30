package com.authorization.services;


import com.authorization.configs.OAuth2Config;

import com.authorization.dto.UserDto;
import com.authorization.dto.UserRegisterDto;
import com.authorization.entities.User;
import com.authorization.exceptions.TargetAlreadyExistsException;
import com.authorization.exceptions.TargetNotFoundException;
import com.authorization.repositories.UserRepository;
import com.authorization.utils.EntityConverter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final EntityConverter entityConverter;

    public UserDto register(UserRegisterDto userToRegister) {
        String username = userToRegister.getUsername();

        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new TargetAlreadyExistsException(username, "User");
        }
        User userCreated = createNewUserDetails(userToRegister);
        User savedUser = userRepository.save(userCreated);
        userRepository.addRole(savedUser.getUsername(), OAuth2Config.Authority.USER.name());
        return entityConverter.transformUserDetails(savedUser);
    }

    private User createNewUserDetails(UserRegisterDto userToRegister) {
        User user = new User();
        user.setUsername(userToRegister.getUsername());
        user.setPassword(userToRegister.getPassword());
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }

    //   public UserDto addRole(UserAuthDto userAuthDto) {
    //       userRepository.addRole(userAuthDto.getUsername(), userAuthDto.getRole().name());
    //      User userOptional = userRepository.findByUsername(userAuthDto.getUsername());
    //      return userOptional
    //             .map(user -> entityConverter.transformUserDetails(user))
    //             .orElseThrow(() -> new InternalErrorException("Can't find user"));
    // }

    public UserRegisterDto login(UserRegisterDto userToLogin) {
        String username = userToLogin.getUsername();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new TargetNotFoundException(username, "user");
        }
        String password = userToLogin.getPassword();
        System.out.print("access token"+obtainAccessToken("food-delivery", username, password));
        return null;
    }

    private String obtainAccessToken(String clientId, String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        Response response = RestAssured.given().auth().preemptive()
                .basic(clientId, "food-delivery-secret").and().with().params(params).when()
                .post("http://localhost:8081/oauth/token");
        return response.jsonPath().getString("access_token");
    }
}
