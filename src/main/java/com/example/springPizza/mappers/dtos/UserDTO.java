package com.example.springPizza.mappers.dtos;

import com.example.springPizza.models.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String login;
    private String password;
    private String phoneNumber;
    private Role role;
}
