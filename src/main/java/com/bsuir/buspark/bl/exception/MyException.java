package com.bsuir.buspark.bl.exception;

import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException {
    public HttpStatus httpStatus;

    public MyException() {
    }

    public MyException(String message) {
        super (message);
    }

    public MyException(Throwable cause) {
        super (cause);
    }

    public MyException(String message, Throwable cause) {
        super (message, cause);
    }
}