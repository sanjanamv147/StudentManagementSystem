package com.example.spring.project.ems.Service;
import com.example.spring.project.ems.DTO.UserDTO;
import com.example.spring.project.ems.Entity.User;
import com.example.spring.project.ems.Mapper.UserMapper;
import com.example.spring.project.ems.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }
}