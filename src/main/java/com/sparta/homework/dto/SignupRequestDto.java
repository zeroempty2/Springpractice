package com.sparta.homework.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignupRequestDto {
    @ApiModelProperty(example = "최소 4자, 최대 10자 영어소문자와 숫자인 이름")
    @Pattern(regexp = "^[0-9a-z].{3,10}$",message = "최소 4자, 최대 10자 영어소문자와 숫자만 입력할 수 있습니다.")
    private String username;
    @ApiModelProperty(example = "최소 8자, 최대15자 영어 대소문자, 특수문자인 비밀번호")
    @Pattern(regexp = "^[A-Za-z0-9#?!@$%^&*-].{7,15}$",message = "최소 8자, 최대15자 영어 대소문자, 특수문자를 입력할 수 있습니다.")
    private String password;
    @ApiModelProperty(example = "관리자 계정 생성 체크 여부")
    private boolean admin = false;
    @ApiModelProperty(example = "관리자 계정 생성시에 받는 토큰, admin과 adminToken은 기본적으로 입력하지 않아도 됨")
    private String adminToken = "";
}
