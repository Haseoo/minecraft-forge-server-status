package com.github.haseoo.minecraft.statusapi.configuration;

import com.github.haseoo.minecraft.statusapi.utils.Constants;
import com.github.haseoo.minecraft.statusapi.views.ErrorView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ErrorView> handleGenericNotFoundException(IOException e) {
        log.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(ErrorView.defaultInstance(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
