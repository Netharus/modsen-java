package com.example.springPizza.database.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "price")
    private BigDecimal price;

    @Column(length = 200, name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;

    @Column(name = "category_id")
    private Integer categoryId;

}
