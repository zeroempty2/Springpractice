package com.sparta.homework.responseMessageData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessages {
    LOGIN_SUCCESS(200,"로그인 성공",true),
    CREATED_USER(200,"회원 가입 성공",true),
    DELETE_SUCCESS(200,"삭제 성공",true);
    private final int statusCode;
    private final String message;
    private final boolean success;
}
