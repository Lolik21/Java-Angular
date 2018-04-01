package com.bsuir.buspark.bl.exception.validation;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;

public class UserValidationException extends MyException {
    public UserValidationException() {
    }

    public UserValidationException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public UserValidationException(Throwable cause) {
        super (cause);
    }

    public UserValidationException(String message, Throwable cause) {
        super (message, cause);
    }
}
