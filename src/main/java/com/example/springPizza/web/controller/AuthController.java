package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.UserRequest;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;

    @PostMapping
    public UserResponse register(@RequestBody UserRequest request){
        return userService.createUser(request);
    }

}
