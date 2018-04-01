package com.bsuir.buspark.bl.exception.notFound;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RoleNotFoundException extends MyException {

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_FOUND;
    }

    public RoleNotFoundException(Throwable cause) {
        super (cause);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}
