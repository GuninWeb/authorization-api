package com.authorizationapi.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String massage) {
        super(massage);
    }
}
