package com.sparta.homework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    @ApiModelProperty(example = "이름")
    private String username;
    @ApiModelProperty(example = "비밀번호")
    private String password;
}