package com.example.springPizza.service;

import com.example.springPizza.database.models.User;
import com.example.springPizza.database.models.dto.UserDTO;
import com.example.springPizza.database.models.enums.Role;
import com.example.springPizza.repositories.UserRepository;

import com.example.springPizza.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByUsername(String username) throws Exception {
        return convertToDTO(userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found")));
    }

    @Override
    public UserDTO getUserByLogin(String login) throws Exception {
        return convertToDTO(userRepository.findByLogin(login).orElseThrow(() -> new Exception("User not found")));
    }


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        return convertToDTO(userRepository.findById(id).orElseThrow(() -> new Exception("User not found")));
    }

    @Transactional
    @Override
    public User createUser(UserDTO userDTO) {
        User user = convertFromDTO(userDTO);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, UserDTO userDTO) throws Exception {
        userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        User user = convertFromDTO(userDTO);
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing user");
        }
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(Role.valueOf(user.getRole().name())); // Assuming role is stored as enum and converted to String
        return userDTO;
    }

    private User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(Role.valueOf(String.valueOf(userDTO.getRole())));
        return user;
    }
}