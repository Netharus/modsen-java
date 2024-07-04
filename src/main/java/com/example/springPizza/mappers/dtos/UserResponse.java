package com.example.springPizza.mappers.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private String role;
}
