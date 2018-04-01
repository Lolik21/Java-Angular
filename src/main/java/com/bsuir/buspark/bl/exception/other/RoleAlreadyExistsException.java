package com.bsuir.buspark.bl.exception.other;

import com.bsuir.buspark.bl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RoleAlreadyExistsException extends MyException {
    public RoleAlreadyExistsException() {
    }

    public RoleAlreadyExistsException(String message) {
        super (message);
        httpStatus = HttpStatus.NOT_MODIFIED;
    }

    public RoleAlreadyExistsException(Throwable cause) {
        super (cause);
    }

    public RoleAlreadyExistsException(String message, Throwable cause) {
        super (message, cause);
    }
}
