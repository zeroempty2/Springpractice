package com.sparta.homework.exception;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ExceptionResponse {
    public int statusCode;
    public String message;
    public boolean success;
}
