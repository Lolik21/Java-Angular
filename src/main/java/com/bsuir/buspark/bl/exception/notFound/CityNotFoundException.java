package com.bsuir.buspark.bl.exception.notFound;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CityNotFoundException extends MyException {
    public CityNotFoundException() {
    }

    public CityNotFoundException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public CityNotFoundException(Throwable cause) {
        super (cause);
    }

    public CityNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}

