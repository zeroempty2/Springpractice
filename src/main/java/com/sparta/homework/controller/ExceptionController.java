package com.sparta.homework.controller;

import com.sparta.homework.exception.*;
import com.sparta.homework.exception.Exception;
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

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidTokenException(InvalidTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_TOKEN.getStatusCode(),Exception.INVALID_TOKEN.getMessage());
    }
    @ExceptionHandler(InvalidWriterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidWriterUpdateException(InvalidWriterException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_WRITER.getStatusCode(),Exception.INVALID_WRITER.getMessage());
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ExceptionResponse notFoundUserException(NotFoundUserException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.NOT_FOUND_USER.getStatusCode(),Exception.NOT_FOUND_USER.getMessage());
    }
    @ExceptionHandler(NotFoundPostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse notFoundPostException(NotFoundPostException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.NOT_FOUND_POST.getStatusCode(),Exception.NOT_FOUND_POST.getMessage());
    }
    @ExceptionHandler(NotFoundCommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse notFoundCommentException(NotFoundCommentException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.NOT_FOUND_COMMENT.getStatusCode(),Exception.NOT_FOUND_COMMENT.getMessage());
    }
    @ExceptionHandler(InvalidUserNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidUserNameException(InvalidUserNameException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_USERNAME.getStatusCode(),Exception.INVALID_USERNAME.getMessage());
    }

    @ExceptionHandler(IsNotAdminTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse isNotAdminToken(IsNotAdminTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.IS_NOT_ADMIN_TOKEN.getStatusCode(),Exception.IS_NOT_ADMIN_TOKEN.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidPasswordException(InvalidPasswordException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_PASSWORD.getStatusCode(),Exception.INVALID_PASSWORD.getMessage());
    }
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse notLoginException(NotLoginException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.PLEASE_LOGIN.getStatusCode(),Exception.PLEASE_LOGIN.getMessage());
    }

}
