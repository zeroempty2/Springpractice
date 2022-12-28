package com.sparta.homework.responseMessageData;

import lombok.*;


@Getter
public class DefaultRes {
    private int statusCode;
    private String message;
    private boolean success;

    public static DefaultRes valueOf(int statusCode, String message, boolean success){
        return new DefaultRes(statusCode,message,success);
    }
    public DefaultRes(int statusCode, String message, boolean success) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }
}


