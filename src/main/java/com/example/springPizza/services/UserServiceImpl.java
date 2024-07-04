package com.example.springPizza.services;

import com.example.springPizza.exceptions.UserNotFoundException;
import com.example.springPizza.mappers.UserMapper;
import com.example.springPizza.mappers.dtos.UserRequest;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.models.User;
import com.example.springPizza.repositories.UserRepository;
import com.example.springPizza.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserByUsername(String username) {
        return userMapper.toResponse(userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserByLogin(String login) {
        return userMapper.toResponse(userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toResponses(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserById(Long id) {
        return userMapper.toResponse(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toModel(request);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User oldUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        User newUser = userMapper.toModel(request);
        newUser.setId(id);
        newUser.setRole(oldUser.getRole());
        return userMapper.toResponse(userRepository.saveAndFlush(newUser));
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing user");
        }
    }

}
