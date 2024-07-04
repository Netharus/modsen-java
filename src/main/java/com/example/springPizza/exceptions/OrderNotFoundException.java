package com.example.springPizza.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){super("Order not found");}
}
