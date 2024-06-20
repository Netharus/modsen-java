package com.example.springPizza.mappers.dtos;

import lombok.Data;

@Data
public class UserRequest {
    private String login;
    private String password;
    private String username;
    private String phoneNumber;
}
