package com.sparta.homework.exception;

import lombok.Getter;


@Getter
public class ExceptionResponse {
    private int statusCode;
    private String message;
    private boolean success;

    public static ExceptionResponse valueOf(int statusCode, String message, boolean success){
        return new ExceptionResponse(statusCode, message, success);
    }

    public ExceptionResponse(int statusCode, String message, boolean success) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }
}
