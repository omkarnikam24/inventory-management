package com.example.inventory.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author omkar
 * @apiNote A Controller Advice which handles all the enclosed exceptions and gives a structured error response
 */
@RestControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<ResponseErrorDetails> handleObjectIdNotValid(OrderNotFoundException exception,
                                                                          WebRequest request) {
        ResponseErrorDetails errorDetails = new ResponseErrorDetails(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<ResponseErrorDetails> handleInvalidRequest(InvalidRequestException exception,
                                                                        WebRequest request) {
        ResponseErrorDetails errorDetails = new ResponseErrorDetails(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OrderCreationException.class)
    protected ResponseEntity<ResponseErrorDetails> handleOrderCreationException(OrderCreationException exception,
                                                                                WebRequest request) {
        ResponseErrorDetails errorDetails = new ResponseErrorDetails(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }

}
