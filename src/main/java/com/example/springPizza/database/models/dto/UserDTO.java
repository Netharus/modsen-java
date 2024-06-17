package com.example.springPizza.database.models.dto;

import com.example.springPizza.database.models.enums.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String login;
    private String password;
    private String phoneNumber;
    private Role role;

}
