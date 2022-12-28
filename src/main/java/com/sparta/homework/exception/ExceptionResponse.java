package com.sparta.homework.exception;

import lombok.Getter;


@Getter
public class ExceptionResponse {
    private int statusCode;
    private String message;
    private boolean success;

    public static ExceptionResponse valueOf(Exception exception){
        return new ExceptionResponse(exception.getStatusCode(), exception.getMessage(), false);
    }

    public ExceptionResponse(int statusCode, String message, boolean success) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }
}
