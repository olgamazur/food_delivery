package com.authorization.services;


import com.authorization.configs.OAuth2Config;
import com.authorization.dto.UserAuthDto;
import com.authorization.dto.UserDto;
import com.authorization.dto.UserRegisterDto;
import com.authorization.entities.User;
import com.authorization.exceptions.InternalErrorException;
import com.authorization.exceptions.TargetAlreadyExistsException;
import com.authorization.exceptions.TargetNotFoundException;
import com.authorization.repositories.UserRepository;
import com.authorization.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final EntityConverter entityConverter;

    public UserDto register(UserRegisterDto userToRegister) {
        String username = userToRegister.getUsername();

        User user = userRepository.findByUsername(username);
        if (user==null) {
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
        log.info("signIn user with {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new TargetNotFoundException(username, "user");
        }

        return null;
    }
}
