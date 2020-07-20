package com.example.inventory.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author omkar
 * @apiNote An Exception which is thrown if item creation fails
 */
public class ItemCreationException extends RuntimeException {

    public ItemCreationException(String message) {
        super(message);
    }
}
