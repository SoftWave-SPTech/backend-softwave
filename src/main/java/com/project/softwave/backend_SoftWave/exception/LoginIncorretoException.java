package com.project.softwave.backend_SoftWave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginIncorretoException extends RuntimeException {
    public LoginIncorretoException(String message) {
        super(message);
    }
}
