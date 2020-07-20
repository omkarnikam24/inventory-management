package com.example.inventory.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author omkar
 * @apiNote An Exception which is thrown if the request is invalid
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
