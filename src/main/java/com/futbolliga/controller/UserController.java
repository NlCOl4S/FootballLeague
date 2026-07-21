package com.futbolliga.controller;

import com.futbolliga.dto.UserResponse;
import com.futbolliga.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<UserResponse> getUsers(){
        final var users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getName(), user.getEmail()))
                .toList();
    }

}
