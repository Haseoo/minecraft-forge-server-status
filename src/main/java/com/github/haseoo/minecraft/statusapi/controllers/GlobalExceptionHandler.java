package com.github.haseoo.minecraft.statusapi.controllers;

import com.github.haseoo.minecraft.statusapi.views.ErrorView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ErrorView> handleGenericNotFoundException(IOException e) {
        var error = ErrorView.defaultInstance();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
