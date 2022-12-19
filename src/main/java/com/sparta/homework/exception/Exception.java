package com.sparta.homework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {
    INVALID_TOKEN(400,"토큰이 유효하지 않습니다.");



    private final int code;
    private final String message;
}
