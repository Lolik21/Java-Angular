package com.bsuir.buspark.bl.exception.other;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CityNowInUseException extends MyException {
    public CityNowInUseException() {
    }

    public CityNowInUseException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_MODIFIED;
    }

    public CityNowInUseException(Throwable cause) {
        super (cause);
    }

    public CityNowInUseException(String message, Throwable cause) {
        super (message, cause);
    }
}