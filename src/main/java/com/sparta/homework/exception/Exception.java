package com.sparta.homework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {
    BAD_REQUEST(400,"잘못된 접근입니다."),
    INVALID_TOKEN(400,"토큰이 유효하지 않습니다."),
    INVALID_WRITER(403,"작성자만 수정/삭제할 수 있습니다. 관리자라면 관리자 url을 이용해 주십시오."),
    INVALID_USERNAME(400,"중복된 username입니다"),
    NOT_FOUND_USER(400,"회원을 찾을 수 없습니다"),
    NOT_FOUND_POST(400,"게시글을 찾을 수 없습니다"),
    NOT_FOUND_COMMENT(400,"댓글을 찾을 수 없습니다"),
    IS_NOT_ADMIN_TOKEN(400,"관리자 계정이 아닙니다"),
    INVALID_PASSWORD(400,"비밀번호가 일치하지 않습니다"),
    PLEASE_LOGIN(400,"로그인 해 주십시오"),
    INVALID_JWT_SIGNATURE(400, "Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."),
    EXPIRED_JWT_TOKEN(400, "Expired JWT token, 만료된 JWT token 입니다."),
    UNSUPPORTED_JWT_TOKEN(400,"Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다."),
    JWT_CLAIMS_IS_EMPTY(400,"JWT claims is empty, 잘못된 JWT 토큰 입니다."),
    INVALID_PATTERN(400,"username은 최소 4자, 최대 10자 영어소문자와 숫자만 입력할 수 있고, password는 최소 8자, 최대15자 영어 대소문자, 특수문자를 입력할 수 있습니다.");


    private final int statusCode;
    private final String message;
}
