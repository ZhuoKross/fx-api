package com.michaelgarnica.fx_api.exceptions;

import com.michaelgarnica.fx_api.Utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handleValidations (MethodArgumentNotValidException errors){
        Map<String, String> validationsErrors = new HashMap<>();

        errors.getBindingResult().getFieldErrors().forEach((err) ->{
            validationsErrors.put(err.getField(), err.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<Map<String, String>>("Request object properties incorrectly configured. Verify and try again", LocalDate.now(), HttpStatus.BAD_REQUEST,validationsErrors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response<String>> handleMalformedRequestBody (){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>("Malformed request body. It couldn't be possible deserialize the object", LocalDate.now(), HttpStatus.BAD_REQUEST,"no data"));
    }

}
