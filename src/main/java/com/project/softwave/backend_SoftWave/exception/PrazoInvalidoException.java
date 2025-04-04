package com.project.softwave.backend_SoftWave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PrazoInvalidoException extends RuntimeException {
    public PrazoInvalidoException(String message) {
        super(message);
    }
}
