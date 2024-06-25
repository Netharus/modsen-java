package com.example.springPizza.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
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
    private String imageUrl;

    @Column(name = "category_id")
    private Integer categoryId;

}
