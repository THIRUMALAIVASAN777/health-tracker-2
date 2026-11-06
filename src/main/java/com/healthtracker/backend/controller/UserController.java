package com.healthtracker.backend.controller;

import com.healthtracker.backend.dto.LoginRequestDTO;
import com.healthtracker.backend.dto.LoginResponseDTO;
import com.healthtracker.backend.dto.UserResponseDTO;
import com.healthtracker.backend.entity.User;
import com.healthtracker.backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid  @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        return userService.login(loginRequest);
    }
}