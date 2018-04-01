package com.bsuir.buspark.bl.exception.other;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TicketAlreadyExistsException extends MyException {
    public TicketAlreadyExistsException() {
    }

    public TicketAlreadyExistsException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_MODIFIED;
    }

    public TicketAlreadyExistsException(Throwable cause) {
        super (cause);
    }

    public TicketAlreadyExistsException(String message, Throwable cause) {
        super (message, cause);
    }
}
