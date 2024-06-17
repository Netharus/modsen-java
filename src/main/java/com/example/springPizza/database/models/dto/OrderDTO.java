package com.example.springPizza.database.models.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
}
