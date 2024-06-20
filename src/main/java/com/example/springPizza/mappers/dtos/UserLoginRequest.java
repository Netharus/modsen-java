package com.example.springPizza.mappers.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest {
    private String login;
    private String password;
}
