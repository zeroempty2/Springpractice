package com.sparta.homework.responseMessageData;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Getter
@Component
public class DefaultRes {
    private int statusCode;
    private String responseMessage;

    public DefaultRes() {
        this.statusCode = StatusCode.BAD_REQUEST;
        this.responseMessage = null;
    }

}


