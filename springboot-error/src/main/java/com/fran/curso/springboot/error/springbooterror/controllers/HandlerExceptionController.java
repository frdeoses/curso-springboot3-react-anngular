package com.fran.curso.springboot.error.springbooterror.controllers;

import com.fran.curso.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.fran.curso.springboot.error.springbooterror.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Error> divisionByZero(Exception e){

        Error error = new Error();
        error.setError("Error division por cero");
        error.setDate(new Date());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);

    }

    @ExceptionHandler({
            NullPointerException.class,
            HttpMessageNotWritableException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<Error> userNotFound(Exception e){

        Error error = new Error();
        error.setError("Error: El usuario no existe o role");
        error.setDate(new Date());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);

    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> numberFormatException(Exception e){

        Map<String,String> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "numero invalido o incorrecto");
        error.put("message",e.getMessage());
        error.put("status",HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return  error;

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException e){

        Error error = new Error();
        error.setError("Api no encontrado");
        error.setDate(new Date());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);

    }


}
