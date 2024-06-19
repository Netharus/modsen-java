package com.example.springPizza.service.interfaces;

import com.example.springPizza.database.models.User;
import com.example.springPizza.database.models.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id) throws Exception;
    UserDTO getUserByUsername(String username) throws Exception;
    UserDTO getUserByLogin(String login) throws Exception;
    User createUser(UserDTO userDTO);
    User updateUser(Long id, UserDTO userDTO) throws Exception;
    void deleteUser(Long id);
}
