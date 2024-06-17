package com.example.springPizza.database.models.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
}
