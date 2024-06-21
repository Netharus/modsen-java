package com.example.springPizza.database.models;

import com.example.springPizza.database.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true, name = "login")
    private String login;

    @Column(length = 50, nullable = false, name = "password")
    private String password;

    @Column(length = 30, nullable = false, name = "username")
    private String username;

    @Column(length = 50, nullable = false, unique = true, name = "email")
    private String email;

    @Column(length = 100, nullable = false, name = "full_name")
    private String fullName;

    @Column(length = 10, nullable = false, name = "gender")
    private String gender;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false, unique = true, name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role = Role.USER;
}
