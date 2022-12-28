package com.sparta.homework.exception;



import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Service
public class ExceptionResponseService {
    public ExceptionResponse getErrorResponse(int statusCode, String message) {
        boolean success = false;
        return ExceptionResponse.valueOf(statusCode,message,success);
    }

    public ExceptionResponse makeMethodArgumentNotValidExceptionResponse(BindingResult bindingResult) {
        int statusCode = 400;
        String message = "지정해주지 않은 유형의 MethodArgumentNotValidException 입니다";
        boolean success = false;
        //에러가 있다면
        if (bindingResult.hasErrors()) {
            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = Objects.requireNonNull(bindingResult.getFieldError()).getCode();
            //유형지정
            switch (Objects.requireNonNull(bindResultCode)) {
                case "Pattern" -> {
                    statusCode = Exception.INVALID_PATTERN.getStatusCode();
                    message = Exception.INVALID_PATTERN.getMessage();
                    return ExceptionResponse.valueOf(statusCode,message,success);
                }
                case "addCase" -> {
                    message = "에러코드와 메세지를 작성해주세요";
                }
            }
        }
        return ExceptionResponse.valueOf(statusCode,message,success);
    }
}
