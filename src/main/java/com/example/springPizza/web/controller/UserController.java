package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.UserRequest;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable(name = "id") Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable(name = "id") Long id ,@RequestBody UserRequest request)  throws Exception {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
    }

//    only for admin

    @PostMapping("/search/{username}")
    public UserResponse findUSerByUsername (@PathVariable(name = "username") String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("/search/{login}")
    public UserResponse findUserByLogin (@PathVariable(name = "login") String login){
        return userService.getUserByLogin(login);
    }

    @PostMapping("/search")
    public List<UserResponse> findAllUsers () {
        return userService.getAllUsers();
    }


}
