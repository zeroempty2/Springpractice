package com.sparta.homework.responseMessageData;

public enum StatusEnum {

    OK(200, "200, OK"),
    BAD_REQUEST(400, "400, BAD_REQUEST"),
    NOT_FOUND(404, "404, NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "500, INTERNAL_SERVER_ERROR");

    int statusCode;
    String code;

    StatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}