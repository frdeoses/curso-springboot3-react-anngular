package com.fran.curso.springboot.error.springbooterror.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
