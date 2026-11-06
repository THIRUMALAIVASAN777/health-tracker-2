package com.healthtracker.backend.service;

import com.healthtracker.backend.dto.LoginRequestDTO;
import com.healthtracker.backend.dto.LoginResponseDTO;
import com.healthtracker.backend.dto.UserResponseDTO;
import com.healthtracker.backend.entity.User;
import com.healthtracker.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getAge()
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponseDTO(
                "Login successful",
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}

