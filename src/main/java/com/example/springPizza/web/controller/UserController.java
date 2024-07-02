package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.UserRequest;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
//    @PreAuthorize("#id == authentication.principal.id || hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("#id == authentication.principal.id)
    public ResponseEntity<UserResponse> updateUser(@PathVariable(name = "id") Long id ,@RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.updateUser(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("#id == authentication.principal.id || hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping ("/username")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> findUserByUsername (@RequestParam(name = "username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/userlogin")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse findUserByLogin(@RequestParam(name = "login") String login) {
        return userService.getUserByLogin(login);
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponse>> findAllUsers () {
        List<UserResponse> userResponseList = userService.getAllUsers();
        if (userResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userResponseList, HttpStatus.OK);
        }
    }
}
