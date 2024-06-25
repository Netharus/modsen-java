package com.example.springPizza.mappers.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String photo;
    private Long categoryId;
}


