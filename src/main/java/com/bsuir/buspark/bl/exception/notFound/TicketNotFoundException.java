package com.bsuir.buspark.bl.exception.notFound;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TicketNotFoundException extends MyException {

    public TicketNotFoundException() {
    }

    public TicketNotFoundException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public TicketNotFoundException(Throwable cause) {
        super (cause);
    }

    public TicketNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}
