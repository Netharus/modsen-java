package com.example.springPizza.services.interfaces;

import com.example.springPizza.mappers.dtos.UserRequest;
import com.example.springPizza.mappers.dtos.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse getUserByUsername(String username);
    UserResponse getUserByLogin(String login);
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id, UserRequest request) throws Exception;
    void deleteUser(Long id);
}
