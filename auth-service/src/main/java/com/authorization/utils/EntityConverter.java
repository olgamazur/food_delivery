package com.authorization.utils;


import com.authorization.dto.UserDetailsDto;
import com.authorization.entities.UserDetails;
import com.authorization.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityConverter {
    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsDto transformUserDetails(UserDetails userDetails) {
        UserDetailsDto dto = new UserDetailsDto(userDetails.getUsername(), userDetails.getPassword(),userDetails.getCreatedDate());
        return dto;
    }
}
