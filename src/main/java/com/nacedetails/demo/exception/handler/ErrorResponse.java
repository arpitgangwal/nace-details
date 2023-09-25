package com.nacedetails.demo.exception.handler;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class ErrorResponse {
    private final String message;
    private final String cause;

    public ErrorResponse(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public String getMessage() {
        return message;
    }
}
