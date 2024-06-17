package com.example.springPizza.database.models;

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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String name;

//    @Builder.Default
//    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
//    private List<Product> products = new ArrayList<>();
}
