package com.example.springPizza.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

//TODO Уточнить необходимость полей, пока поставлю NotBlanck
//TODO сделать защиту для ввода данных
@Data
public class SignUpRequest {

    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Size(min=8, max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;

    @Size(max = 16, message = "Длина номер не должна быть больше 16 символов")
    @NotBlank(message = "Номер телефона не может быть пустыми")
    private String phoneNumber;

    private LocalDate birthDate;

    @Size(max = 100, message = "Длина имени не должна быть больше 100 символов")
    @NotBlank(message = "ФИО не может быть пустыми")
    private String fullName;

    @Size(max = 10, message = "Длина гендера не должна быть больше 100 символов")
    @NotBlank(message = "Пол не может быть пустыми")
    private String gender;
}
