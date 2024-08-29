package com.springeboot.example.ems.backend.dto;



import com.springeboot.example.ems.backend.entity.User;  // Ensure this import is correct for your User entity
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String role;

    public static UserDto mapToUserDto(User user) {  // Updated method name and parameters
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
}
