package com.project.softwave.backend_SoftWave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenExpiradoInvalidoException extends RuntimeException {
    public TokenExpiradoInvalidoException(String message) {
        super(message);
    }
}

