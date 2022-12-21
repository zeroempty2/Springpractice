package com.sparta.homework.exception;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ExceptionResponse {
    int statusCode;
    String message;
    boolean success;
}
