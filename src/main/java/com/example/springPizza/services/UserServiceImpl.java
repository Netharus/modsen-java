package com.example.springPizza.services;

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

    //TODO: custom errors
    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserByUsername(String username) {
        return userMapper.toResponse(userRepository.findByUsername(username).orElseThrow(RuntimeException::new));
    }

    //TODO: custom errors
    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserByLogin(String login) {
        return userMapper.toResponse(userRepository.findByLogin(login).orElseThrow(RuntimeException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toResponses(userRepository.findAll());
    }

    //TODO: custom errors
    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserById(Long id) {
        return userMapper.toResponse(userRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toModel(request);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    //TODO: custom errors
    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        userRepository.findByLogin(request.getLogin()).orElseThrow(RuntimeException::new);
        User user = userMapper.toModel(request);
        user.setId(id);
        return userMapper.toResponse(userRepository.saveAndFlush(user));
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
