package com.bsuir.buspark.bl.exception.validation;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;

public class BusValidationException extends MyException {
    public BusValidationException() {
    }

    public BusValidationException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public BusValidationException(Throwable cause) {
        super (cause);
    }

    public BusValidationException(String message, Throwable cause) {
        super (message, cause);
    }
}

