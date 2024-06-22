package com.example.springPizza.mappers.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private MultipartFile photo;
    private String categoryName;
}
