package com.bsuir.buspark.bl.exception.other;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BusIsNowInUseException extends MyException {
    public BusIsNowInUseException() {
    }

    public BusIsNowInUseException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_MODIFIED;
    }

    public BusIsNowInUseException(Throwable cause) {
        super (cause);
    }

    public BusIsNowInUseException(String message, Throwable cause) {
        super (message, cause);
    }
}