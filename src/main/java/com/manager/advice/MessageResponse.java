package com.manager.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * The MessageResponse class is required if we want to create message response.
 */

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String request;

    public MessageResponse(String message, WebRequest request) {
        this.statusCode = HttpStatus.BAD_REQUEST.value();
        this.timestamp = new Date();
        this.request = request.getDescription(false);
        this.message = message;
    }

    public MessageResponse(int statusCode, String message, WebRequest request) {
        this(message, request);
        this.statusCode = statusCode;
    }
}
