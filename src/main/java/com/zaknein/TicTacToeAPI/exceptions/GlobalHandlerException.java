package com.zaknein.TicTacToeAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zaknein.TicTacToeAPI.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalHandlerException {
    
    @ExceptionHandler(value = InvalidEmailException.class)
    public ErrorResponse notFound(InvalidEmailException ex) {

        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
