package com.bsuir.buspark.bl.exception.validation;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;

public class TicketValidationException extends MyException {
    public TicketValidationException() {
    }

    public TicketValidationException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public TicketValidationException(Throwable cause) {
        super (cause);
    }

    public TicketValidationException(String message, Throwable cause) {
        super (message, cause);
    }
}
