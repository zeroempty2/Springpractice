package com.sparta.homework.responseMessageData;

import lombok.*;


@Getter
public class DefaultRes {
    private int statusCode;
    private String message;
    private boolean success;

    public static DefaultRes valueOf(ResponseMessages responseMessages){
        return new DefaultRes(responseMessages.getStatusCode(), responseMessages.getMessage(), responseMessages.isSuccess());
    }
    public DefaultRes(int statusCode, String message, boolean success) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }
}


