package com.example.springPizza.mappers.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {
    @Size(min = 3, max = 39, message = "The category name must be between 3 and 39 characters")
    private String name;
}
