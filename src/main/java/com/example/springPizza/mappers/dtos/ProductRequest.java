package com.example.springPizza.mappers.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @Size(min = 3, max = 39, message = "The product name must be between 3 and 39 characters")
    private String name;
    @DecimalMin(value = "0.0",message = "The price must be greater than or equal to zero")
    private BigDecimal price;
    @Size(max = 199, message = "The product description must be less than 199 characters")
    private String description;
    private MultipartFile image;
    @Size(min = 3, max = 39, message = "The category name must be between 3 and 39 characters")
    private String categoryName;
}
