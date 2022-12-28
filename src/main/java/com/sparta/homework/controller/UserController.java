package com.sparta.homework.controller;

import com.sparta.homework.dto.LoginRequestDto;
import com.sparta.homework.dto.SignupRequestDto;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.responseMessageData.ResponseMessageService;
import com.sparta.homework.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Api(tags = {"계정 생성, 로그인 api"})
public class UserController {

    private final UserService userService;
    private final ResponseMessageService responseMessageService;


    @PostMapping("/signup")
    @ApiOperation(value = "계정 생성", notes = "계정을 생성한다.")
    public ResponseEntity<DefaultRes> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return responseMessageService.signupOk();
    }

    @ResponseBody
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인을 하고 헤더에 AUTHORIZATION을 반환한다.")
    public ResponseEntity<DefaultRes> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return responseMessageService.loginOk();
    }
}