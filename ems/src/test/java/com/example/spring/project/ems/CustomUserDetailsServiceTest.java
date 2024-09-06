package com.example.spring.project.ems;



import com.example.spring.project.ems.Entity.User;
import com.example.spring.project.ems.Repositories.UserRepository;
import com.example.spring.project.ems.Service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        // Arrange
        User userEntity = new User();
        userEntity.setUsername("john_doe");
        userEntity.setPassword("password123");
        userEntity.setRole("ROLE_USER");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userEntity));

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("john_doe");

        // Assert
        assertNotNull(userDetails);
        assertEquals("john_doe", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("ROLE_USER")
        ));
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("unknown_user");
        });
    }
}
