package com.sparta.homework.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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
    @ExceptionHandler(ExpiredJwtTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse expiredJwtTokenException(ExpiredJwtTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.EXPIRED_JWT_TOKEN.getStatusCode(),Exception.EXPIRED_JWT_TOKEN.getMessage());
    }
    @ExceptionHandler(InvalidJwtSignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse invalidJwtSignatureException(InvalidJwtSignatureException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.INVALID_JWT_SIGNATURE.getStatusCode(),Exception.INVALID_JWT_SIGNATURE.getMessage());
    }
    @ExceptionHandler(JwtClaimsIsEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse expiredJwtTokenException(JwtClaimsIsEmptyException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.JWT_CLAIMS_IS_EMPTY.getStatusCode(),Exception.JWT_CLAIMS_IS_EMPTY.getMessage());
    }
    @ExceptionHandler(UnsupportedJwtTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ExceptionResponse expiredJwtTokenException(UnsupportedJwtTokenException e){
        log.info(e.getMessage());
        return exceptionResponseService.getErrorResponse(Exception.UNSUPPORTED_JWT_TOKEN.getStatusCode(),Exception.UNSUPPORTED_JWT_TOKEN.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생 url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        log.info(e.getMessage());
        return exceptionResponseService.makeMethodArgumentNotValidExceptionResponse(e.getBindingResult());
    }
}
