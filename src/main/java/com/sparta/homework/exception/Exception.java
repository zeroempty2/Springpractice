package com.sparta.homework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {
    INVALID_TOKEN(400,"토큰이 유효하지 않습니다."),
    INVALID_WRITER(403,"작성자만 수정/삭제할 수 있습니다."),
    INVALID_USERNAME(400,"중복된 username입니다"),
    NOT_FOUND_USER(400,"회원을 찾을 수 없습니다"),
    NOT_FOUND_POST(400,"게시글을 찾을 수 없습니다"),
    NOT_FOUND_COMMENT(400,"댓글을 찾을 수 없습니다"),
    IS_NOT_ADMIN_TOKEN(400,"관리자 토큰이 일치하지 않습니다"),
    INVALID_PASSWORD(400,"비밀번호가 일치하지 않습니다"),
    PLEASE_LOGIN(400,"로그인 해 주십시오");


    private final int statusCode;
    private final String message;
}
