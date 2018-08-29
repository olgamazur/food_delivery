package com.authorization.utils;




import com.authorization.dto.UserDto;
import com.authorization.entities.User;
import com.authorization.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityConverter {
    private final UserRepository userRepository;

   public UserDto transformUserDetails(User user) {
        UserDto dto = new UserDto(user.getUsername(), user.getPassword(),user.getCreatedDate());
        return dto;
  }
}
