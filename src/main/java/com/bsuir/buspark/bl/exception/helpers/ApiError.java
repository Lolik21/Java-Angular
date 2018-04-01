package com.bsuir.buspark.bl.exception.helpers;

import com.bsuir.buspark.bl.exception.MyException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus httpStatus;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    private ApiError() {
        timestamp = LocalDateTime.now();
    }


    ApiError(MyException ex) {
        this();
        this.message = ex.getMessage();
        this.httpStatus = ex.httpStatus;
    }
}