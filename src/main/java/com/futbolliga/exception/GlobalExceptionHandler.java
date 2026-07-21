package com.futbolliga.exception;

import com.futbolliga.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                e.getMessage(),
                LocalDateTime.now()
        );
        //ResponseEntity envia mediante Jackson el error
        return new ResponseEntity<>(error,
                HttpStatus.NOT_FOUND); //Error 404
    }

}
