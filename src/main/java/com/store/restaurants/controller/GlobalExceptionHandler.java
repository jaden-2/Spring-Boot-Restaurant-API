package com.store.restaurants.controller;

import com.store.restaurants.entity.Message;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Message> handleDataIntegrityVoilation(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body(new Message(e.getMostSpecificCause().getMessage()));
    }
}
