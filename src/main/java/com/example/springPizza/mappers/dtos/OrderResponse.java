package com.example.springPizza.mappers.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private String login;
    private List<ProductResponse> products;
}
