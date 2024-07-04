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

    @ExceptionHandler({
            UserNotFoundException.class,
            ImageNotFoundException.class,
            CategoryNotFoundException.class,
            OrderNotFoundException.class,
            ProductNotFoundException.class
    })
    ResponseEntity<HttpStatus> handleNotFoundExceptions(RuntimeException ex) {
        log.info("{} exception!!!", ex.getClass().getSimpleName());
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

    @ExceptionHandler({
            IOException.class,
            ImgurUploadException.class,
            ImgurDeleteException.class
    })
    ResponseEntity<HttpStatus> handleInternalServerErrorExceptions(RuntimeException ex) {
        log.error("{} exception: {}", ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
