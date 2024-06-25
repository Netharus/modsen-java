package com.example.springPizza.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionResolver extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    ResponseEntity<HttpStatus> userNotFoundException(){
        log.info("UserNotFound exception!!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ImageNotFoundException.class)
    ResponseEntity<HttpStatus> imageNotFoundException(){
        log.info("ImageNotFound exception!!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    ResponseEntity<HttpStatus> categoryNotFoundException(){
        log.info("CategoryNotFound exception!!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    ResponseEntity<HttpStatus> orderNotFoundException(){
        log.info("OrderNotFound exception!!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    ResponseEntity<HttpStatus> productNotFoundException(){
        log.info("ProductNotFound exception!!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<HttpStatus> illegalArgumentException(){
        log.info("IllegalArgument exception!!!");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OptimisticLockingFailureException.class)
    ResponseEntity<HttpStatus> optimisticLockingFailureException(){
        log.info("OptimisticLockingFailure exception!!!");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = IOException.class)
    ResponseEntity<HttpStatus> IOException(){
        log.info("IO exception!!!, failed to update profile image");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
