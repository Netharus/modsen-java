package com.example.springPizza.mappers.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String login;
    private List<Long> productsId;
}
