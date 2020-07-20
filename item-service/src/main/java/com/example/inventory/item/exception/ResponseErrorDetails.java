package com.example.inventory.item.exception;

import java.io.Serializable;
import java.util.Date;

/**
 * @author omkar
 * @apiNote Response Error skeleton which will be used for sending back error responses
 */
public class ResponseErrorDetails implements Serializable {

    private static final long serialVersionUID = 6330396850138860023L;
    private Date timestamp;
    private String message;
    private String details;

    public ResponseErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
