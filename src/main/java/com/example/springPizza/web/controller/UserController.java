package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.mappers.dtos.UserRequest;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable(name = "id") Long id ,@RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
    }

    @PostMapping("/search/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse findUSerByUsername (@PathVariable(name = "username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/search/{login}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse findUserByLogin (@PathVariable(name = "login") String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponse>> findAllUsers () {
        List<UserResponse> userResponseList = userService.getAllUsers();
        if (userResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userResponseList, HttpStatus.OK);
        }
    }
}
