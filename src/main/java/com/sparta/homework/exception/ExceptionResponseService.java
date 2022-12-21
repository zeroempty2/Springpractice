package com.sparta.homework.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExceptionResponseService {
    private final ExceptionResponse exceptionResponse;

    public ExceptionResponse getErrorResponse(int statusCode, String message) {
        exceptionResponse.statusCode = statusCode;
        exceptionResponse.message = message;
        exceptionResponse.success = false;
        return exceptionResponse;
    }

    public ExceptionResponse makeMethodArgumentNotValidExceptionResponse(BindingResult bindingResult) {
        exceptionResponse.statusCode = 400;
        exceptionResponse.message = "지정해주지 않은 유형의 MethodArgumentNotValidException 입니다";
        exceptionResponse.success = false;
        //에러가 있다면
        if (bindingResult.hasErrors()) {
            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = Objects.requireNonNull(bindingResult.getFieldError()).getCode();
            //유형지정
            switch (Objects.requireNonNull(bindResultCode)) {
                case "Pattern" -> {
                    exceptionResponse.statusCode = Exception.INVALID_PATTERN.getStatusCode();
                    exceptionResponse.message = Exception.INVALID_PATTERN.getMessage();
                }
                case "addCase" -> {
                    exceptionResponse.statusCode = 400;
                    exceptionResponse.message = "에러코드와 메세지를 작성해주세요";
                }
            }
        }
        return exceptionResponse;
    }
}
