package com.example.server1.exceprions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsersNotFoundException extends AbstractException {

    public UsersNotFoundException(String message, String details) {
        super(message, details);
    }

}
