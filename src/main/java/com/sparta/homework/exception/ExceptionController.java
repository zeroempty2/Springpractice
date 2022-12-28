package com.sparta.homework.exception;

import com.sparta.homework.exception.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final ExceptionResponseService exceptionResponseService;
    private final HttpHeaders headers;

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidTokenException(InvalidTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_TOKEN);
    }
    @ExceptionHandler(InvalidWriterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidWriterUpdateException(InvalidWriterException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_WRITER);
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ExceptionResponse notFoundUserException(NotFoundUserException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.NOT_FOUND_USER);
    }
    @ExceptionHandler(NotFoundPostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse notFoundPostException(NotFoundPostException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.NOT_FOUND_POST);
    }
    @ExceptionHandler(NotFoundCommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse notFoundCommentException(NotFoundCommentException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.NOT_FOUND_COMMENT);
    }
    @ExceptionHandler(InvalidUserNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidUserNameException(InvalidUserNameException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_USERNAME);
    }

    @ExceptionHandler(IsNotAdminTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse isNotAdminToken(IsNotAdminTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.IS_NOT_ADMIN_TOKEN);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidPasswordException(InvalidPasswordException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_PASSWORD);
    }
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse notLoginException(NotLoginException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.PLEASE_LOGIN);
    }
    @ExceptionHandler(ExpiredJwtTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse expiredJwtTokenException(ExpiredJwtTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.EXPIRED_JWT_TOKEN);
    }
    @ExceptionHandler(InvalidJwtSignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidJwtSignatureException(InvalidJwtSignatureException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_JWT_SIGNATURE);
    }
    @ExceptionHandler(JwtClaimsIsEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse expiredJwtTokenException(JwtClaimsIsEmptyException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.JWT_CLAIMS_IS_EMPTY);
    }
    @ExceptionHandler(UnsupportedJwtTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse expiredJwtTokenException(UnsupportedJwtTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.UNSUPPORTED_JWT_TOKEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생 url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        log.info(e.getMessage());
        return exceptionResponseService.makeMethodArgumentNotValidExceptionResponse(e.getBindingResult());
    }
    //test
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ExceptionResponse> expiredJwtTokenException(ExpiredJwtException e){
        log.info(e.getMessage());
        headers.setContentType(new MediaType("application","json", StandardCharsets.UTF_8));
        ExceptionResponse exceptionResponse =  exceptionResponseService.getErrorResponse(Exception.EXPIRED_JWT_TOKEN);
        return new ResponseEntity<>(exceptionResponse,headers,HttpStatus.BAD_REQUEST);
    }
}
