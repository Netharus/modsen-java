package com.example.springPizza.exceptions;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(){
        super("Image not found");
    }
}
