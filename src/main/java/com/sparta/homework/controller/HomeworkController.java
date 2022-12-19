package com.sparta.homework.controller;

import com.sparta.homework.dto.HomeworkRequestDto;
import com.sparta.homework.dto.HomeworkResponseByIdDto;
import com.sparta.homework.dto.HomeworkResponseDto;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.responseMessageData.ResponseMessage;
import com.sparta.homework.responseMessageData.StatusCode;
import com.sparta.homework.service.HomeworkService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeworkController {
    private final HomeworkService homeworkService;
    private final DefaultRes defaultRes;
    private final JwtUtil jwtUtil;

    @PostMapping("/post")
    public HomeworkRequestDto createHomework(@RequestBody HomeworkRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }
            String userNameToken = claims.getSubject();
            return homeworkService.createHomework(requestDto, userNameToken);
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }
    @GetMapping("/posts")
    public List<HomeworkResponseDto> getHomeworks(){
        return  homeworkService.getHomeworks();
    }
    @GetMapping("/post/{id}")
    public HomeworkResponseByIdDto getSelectHomeworks(@PathVariable Long id){
       return homeworkService.getSelectHomeworks(id);
    }
    @PutMapping("/post/{id}")
    public HomeworkRequestDto updateHomework(@PathVariable Long id, @RequestBody HomeworkRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }
            String userNameToken = claims.getSubject();
            return homeworkService.update(id,requestDto,userNameToken);
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<DefaultRes> deleteHomework(@PathVariable Long id, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }
            String userNameToken = claims.getSubject();
            homeworkService.delete(id,userNameToken);
            defaultRes.setStatusCode(StatusCode.OK);
            defaultRes.setResponseMessage(ResponseMessage.DELETE_SUCCESS);
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }
}

