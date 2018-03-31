package com.bsuir.buspark.bl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
    }

    public CityNotFoundException(String message) {
        super (message);
    }

    public CityNotFoundException(Throwable cause) {
        super (cause);
    }

    public CityNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}

