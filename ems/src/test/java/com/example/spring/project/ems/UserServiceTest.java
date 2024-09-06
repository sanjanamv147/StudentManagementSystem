package com.example.spring.project.ems;



import com.example.spring.project.ems.DTO.UserDTO;
import com.example.spring.project.ems.Entity.User;
import com.example.spring.project.ems.Mapper.UserMapper;
import com.example.spring.project.ems.Repositories.UserRepository;
import com.example.spring.project.ems.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newuser");
        userDTO.setPassword("password");
        userDTO.setRole("ROLE_USER");

        User userEntity = new User();
        userEntity.setUsername("newuser");
        userEntity.setPassword("password");
        userEntity.setRole("ROLE_USER");

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.empty());
        when(userMapper.toEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toDTO(userEntity)).thenReturn(userDTO);

        // Act
        UserDTO createdUser = userService.createUser(userDTO);

        // Assert
        assertEquals(userDTO.getUsername(), createdUser.getUsername());
        assertEquals(userDTO.getPassword(), createdUser.getPassword());
        assertEquals(userDTO.getRole(), createdUser.getRole());

        verify(userRepository).findByUsername(userDTO.getUsername());
        verify(userRepository).save(userEntity);
        verify(userMapper).toDTO(userEntity);
    }

    @Test
    void testCreateUser_UsernameAlreadyExists() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("existinguser");
        userDTO.setPassword("password");
        userDTO.setRole("ROLE_USER");

        User existingUser = new User();
        existingUser.setUsername("existinguser");

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.of(existingUser));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.createUser(userDTO));

        assertEquals("Username already exists!", exception.getMessage());

        verify(userRepository).findByUsername(userDTO.getUsername());
    }
}
