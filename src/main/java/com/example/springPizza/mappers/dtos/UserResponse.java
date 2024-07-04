package com.example.springPizza.mappers.dtos;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String login;
    private String username;
    private String phoneNumber;
    private String role;
}
