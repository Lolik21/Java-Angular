package com.bsuir.buspark.bl.exception.helpers;

import com.bsuir.buspark.bl.exception.BusNotFoundException;
import com.bsuir.buspark.bl.exception.CityNotFoundException;
import com.bsuir.buspark.bl.exception.RoleNotFoundException;
import com.bsuir.buspark.bl.exception.TicketNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusNotFoundException.class)
    protected ResponseEntity<Object> handleBusNotFound(
            BusNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    protected ResponseEntity<Object> handleTicketNotFound(
            TicketNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(CityNotFoundException.class)
    protected ResponseEntity<Object> handleCityNotFound(
            CityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    protected ResponseEntity<Object> handleRoleNotFound(
            RoleNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
