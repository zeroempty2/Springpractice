package com.sparta.homework.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "^[0-9a-z].{4,10}$",message = "최소 4자, 최대 10자 영어소문자와 숫자만 입력할 수 있습니다")
    private String username;
    @Pattern(regexp = "^[A-Za-z0-9#?!@$%^&*-].{8,15}$",message = "최소 8자, 최대15자 영어 대소문자, 특수문자를 입력할 수 있습니다.")
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
