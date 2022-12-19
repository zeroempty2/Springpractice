package com.sparta.homework.service;

import com.sparta.homework.exception.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExceptionResponseService {
   private final ExceptionResponse exceptionResponse;
    public ExceptionResponse getErrorResponse(int statusCode, String message){
        exceptionResponse.statusCode = statusCode;
        exceptionResponse.message = message;
        exceptionResponse.success = false;
        return exceptionResponse;
    }
}
