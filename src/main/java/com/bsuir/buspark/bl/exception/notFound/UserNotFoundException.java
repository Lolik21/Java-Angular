package com.bsuir.buspark.bl.exception.notFound;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends MyException {

    public HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public UserNotFoundException(Throwable cause) {
        super (cause);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}
