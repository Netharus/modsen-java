package com.example.springPizza.mappers.dtos;

import com.example.springPizza.database.models.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String login;
    private String password;
    private String email;
    private String fullName;
    private String gender;
    private LocalDate birthDay;
    private String phoneNumber;
    private Role role;
}
