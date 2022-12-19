package com.sparta.homework.service;

import com.sparta.homework.exception.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExceptionResponseService {
   private final ExceptionResponse exceptionResponse;
    public ExceptionResponse getErrorResponse(int code, String message){
        exceptionResponse.code = code;
        exceptionResponse.message = message;
        exceptionResponse.success = false;
        return exceptionResponse;
    }
}
