package com.authorization.services;


import com.authorization.repositories.UserRepository;
import com.authorization.utils.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final EntityConverter entityConverter;

  /*  public UserDetailsDto register(RegisteredUserDetailsDto userToRegister) {
        String username = userToRegister.getUsername();

  //      Optional<UserDetails> userOptional = userDetailsRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            throw new TargetAlreadyExistsException(username, "User");
        }
        UserDetails user = createNewUserDetails(userToRegister);
    //    UserDetails savedUser = userDetailsRepository.save(user);
//        userDetailsRepository.addRole(savedUser.getUsername(),OAuth2ServerConfig.Authority.USER.name());
  //      return entityConverter.transformUserDetails(savedUser);
    }

    private UserDetails createNewUserDetails(RegisteredUserDetailsDto userToRegister) {
        UserDetails user = new UserDetails();
        user.setUsername(userToRegister.getUsername());
        user.setPassword(userToRegister.getPassword());
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }
   /* public UserDetailsDto addRole(UserAuthDetailsDto userAuthDetailsDto) {
        userDetailsRepository.addRole(userAuthDetailsDto.getUsername(), userAuthDetailsDto.getRole().name());
        Optional<UserDetails> userOptional = userDetailsRepository.findByUsername(userAuthDetailsDto.getUsername());
        return userOptional
                .map(user -> entityConverter.transformUserDetails(user))
                .orElseThrow(() -> new InternalErrorException("Can't find user"));
    }*/
}
