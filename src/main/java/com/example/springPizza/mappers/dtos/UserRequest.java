package com.example.springPizza.mappers.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @Size(min = 4, max = 29, message = "The login must be between 4 and 29 characters")
    private String login;
    @Size(min = 4, max = 20, message = "The password must be between 4 and 20 characters")
    private String password;
    @Size(min = 4, max = 29, message = "The username must be between 4 and 29 characters")
    private String username;
    @Size(min = 9, max = 9, message = "The phone number must be 9 characters") // я считаю без 375, а только например 29 7342243
    private String phoneNumber;
}
