package com.authorization.services;

import com.authorization.dto.RegisteredUserDetailsDto;
import com.authorization.dto.UserDetailsDto;
import com.authorization.entities.UserDetails;
import com.authorization.repositories.UserDetailsRepository;
import com.authorization.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
   private final UserDetailsRepository userDetailsRepository;
   private EntityConverter entityConverter;
  /*  public UserDetailsDto register(RegisteredUserDetailsDto userToRegister) {
        String username = userToRegister.getUsername();

    //    Optional<UserDetails> userOptional = userDetailsRepository.findById(username);
      //  if (userOptional.isPresent()) {
     //       throw new UsernameExistsException(username);
    //    }
        UserDetails user = createNewUserDetails(userToRegister);
        UserDetails savedUser = userDetailsRepository.save(user);
       // userDetailsRepository.addRole(savedUser.getUsername(), OAuth2ServerConfig.Authority.USER.name());
        return entityConverter.transformUserDetails(savedUser);
    }
    private UserDetails createNewUserDetails(RegisteredUserDetailsDto userToRegister) {
        UserDetails user = new UserDetails();
        user.setUsername(userToRegister.getUsername());
        user.setPassword(userToRegister.getPassword());
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }     */
}
