package com.authorization.utils;



import com.authorization.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityConverter {
    private final UserRepository userRepository;

 /*   public UserDetailsDto transformUserDetails(UserDetails userDetails) {
        UserDetailsDto dto = new UserDetailsDto(userDetails.getUsername(), userDetails.getPassword(),userDetails.getCreatedDate());
        return dto;
  } */
}
