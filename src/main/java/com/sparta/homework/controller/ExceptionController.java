package com.sparta.homework.controller;

import com.sparta.homework.exception.Exception;
import com.sparta.homework.exception.ExceptionResponse;
import com.sparta.homework.exception.InvalidException;
import com.sparta.homework.service.ExceptionResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final ExceptionResponseService exceptionResponseService;

    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse InvalidTokenException(InvalidException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_TOKEN.getCode(),Exception.INVALID_TOKEN.getMessage());
    }
}
