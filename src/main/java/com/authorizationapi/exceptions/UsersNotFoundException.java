package com.authorizationapi.exceptions;

public class UsersNotFoundException extends Exception {
    public UsersNotFoundException(String message) {
        super(message);
    }
}
