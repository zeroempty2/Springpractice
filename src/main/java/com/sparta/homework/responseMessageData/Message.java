package com.sparta.homework.responseMessageData;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Component
public class Message {
    private StatusEnum status;
    private String message;


    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
    }
}


