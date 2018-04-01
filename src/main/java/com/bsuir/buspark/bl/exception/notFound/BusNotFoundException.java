package com.bsuir.buspark.bl.exception.notFound;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BusNotFoundException extends MyException {

    public BusNotFoundException() {
    }

    public BusNotFoundException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public BusNotFoundException(Throwable cause) {
        super (cause);
    }

    public BusNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}
