package com.springeboot.example.ems.backend.mapper;


import com.springeboot.example.ems.backend.dto.UserDto;
import com.springeboot.example.ems.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convert User entity to UserDto
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    // Convert UserDto to User entity
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }
}
