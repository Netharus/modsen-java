package com.example.springPizza.mappers.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {
    @Size(min = 4, max = 29, message = "The login must be between 4 and 29 characters")
    private String username;
    @Size(min = 4, max = 20, message = "The password must be between 4 and 20 characters")
    private String password;
}
