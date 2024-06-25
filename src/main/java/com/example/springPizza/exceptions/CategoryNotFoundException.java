package com.example.springPizza.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        super("Category not found");
    }
}
