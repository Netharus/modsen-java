package com.example.springPizza.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){super("Product not found!!!");}
}
