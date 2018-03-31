package com.bsuir.buspark.bl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusNotFoundException extends RuntimeException {
    public BusNotFoundException() {
    }

    public BusNotFoundException(String message) {
        super (message);
    }

    public BusNotFoundException(Throwable cause) {
        super (cause);
    }

    public BusNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}
