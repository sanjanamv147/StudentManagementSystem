package com.example.spring.project.ems;

import com.example.spring.project.ems.DTO.UserDTO;
import com.example.spring.project.ems.Entity.User;
import com.example.spring.project.ems.Mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserMapperTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToEntity() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("testuser");
        userDTO.setPassword("password");
        userDTO.setRole("user");

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);

        // Act
        User userEntity = userMapper.toEntity(userDTO);

        // Assert
        assertEquals(userDTO.getId(), userEntity.getId());
        assertEquals(userDTO.getUsername(), userEntity.getUsername());
        assertEquals(encodedPassword, userEntity.getPassword());
        assertEquals("ROLE_USER", userEntity.getRole());
    }

    @Test
    void testToDTO() {
        // Arrange
        User userEntity = new User();
        userEntity.setId(1L);
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedPassword");
        userEntity.setRole("ROLE_USER");

        // Act
        UserDTO userDTO = userMapper.toDTO(userEntity);

        // Assert
        assertEquals(userEntity.getId(), userDTO.getId());
        assertEquals(userEntity.getUsername(), userDTO.getUsername());
        assertEquals(userEntity.getPassword(), userDTO.getPassword());
        assertEquals("USER", userDTO.getRole());
    }
}

