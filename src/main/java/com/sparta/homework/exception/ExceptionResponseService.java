package com.sparta.homework.exception;



import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Service
public class ExceptionResponseService {
    public ExceptionResponse getErrorResponse(Exception exception) {
        return ExceptionResponse.valueOf(exception);
    }

    public ExceptionResponse makeMethodArgumentNotValidExceptionResponse(BindingResult bindingResult) {
        //에러가 있다면
        if (bindingResult.hasErrors()) {
            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = Objects.requireNonNull(bindingResult.getFieldError()).getCode();
            //유형지정
            switch (Objects.requireNonNull(bindResultCode)) {
                case "Pattern" -> {
                    return ExceptionResponse.valueOf(Exception.INVALID_PATTERN);
                }
                case "addCase" -> {
                    return ExceptionResponse.valueOf(Exception.NONE);
                }
            }
        }
        return ExceptionResponse.valueOf(Exception.NONE);
    }
}
