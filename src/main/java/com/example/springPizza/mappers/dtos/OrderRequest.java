package com.example.springPizza.mappers.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private Long userId;

    private List<Long> productsId;
}
