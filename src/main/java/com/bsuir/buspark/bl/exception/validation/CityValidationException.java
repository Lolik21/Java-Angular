package com.bsuir.buspark.bl.exception.validation;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;

public class CityValidationException extends MyException {
    public CityValidationException() {
    }

    public CityValidationException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public CityValidationException(Throwable cause) {
        super (cause);
    }

    public CityValidationException(String message, Throwable cause) {
        super (message, cause);
    }
}
