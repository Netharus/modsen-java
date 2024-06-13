package com.example.springPizza.database.models;

import com.example.springPizza.database.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String login;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Order> orders = new ArrayList<>();
}
