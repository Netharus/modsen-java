package com.example.springPizza.exceptions;

public class ImgurDeleteException extends RuntimeException {
    public ImgurDeleteException(String message) {
        super(message);
    }

    public ImgurDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
