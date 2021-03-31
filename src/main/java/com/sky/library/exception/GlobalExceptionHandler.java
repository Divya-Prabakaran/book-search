package com.sky.library.exception;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import com.sky.library.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * @Author: Divya Prabakaran
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

    // handling book not found exception
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> bookNotFoundHandling(BookNotFoundException exception, WebRequest request){
        ApiError apiError =
                new ApiError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ApiError apiError =
                new ApiError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
