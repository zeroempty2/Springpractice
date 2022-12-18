package com.sparta.homework.controller;

import com.sparta.homework.dto.LoginRequestDto;
import com.sparta.homework.dto.SignupRequestDto;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.responseMessageData.ResponseMessage;
import com.sparta.homework.responseMessageData.StatusCode;
import com.sparta.homework.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final DefaultRes defaultRes;


    @PostMapping("/signup")
    public ResponseEntity<DefaultRes> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        defaultRes.setStatusCode(StatusCode.OK);
        defaultRes.setResponseMessage(ResponseMessage.CREATED_USER);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<DefaultRes> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        defaultRes.setStatusCode(StatusCode.OK);
        defaultRes.setResponseMessage(ResponseMessage.LOGIN_SUCCESS);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }
}