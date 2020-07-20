package com.example.inventory.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author omkar
 * @apiNote An Exception which is thrown if order creation fails
 */
public class OrderCreationException extends RuntimeException {

    public OrderCreationException(String message) {
        super(message);
    }
}
