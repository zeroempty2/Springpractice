package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.responseMessageData.ResponseMessage;
import com.sparta.homework.responseMessageData.StatusCode;
import com.sparta.homework.service.CommentService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{id}")
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final DefaultRes defaultRes;
    @PostMapping("/comment")
    public CommentRequestDto addComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }
            String userNameToken = claims.getSubject();
            return commentService.addComment(requestDto, userNameToken, id);
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }
    @PutMapping("/comment/{commentId}")
    public CommentRequestDto updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }
            String userNameToken = claims.getSubject();
            return commentService.updateComment(commentId,id,requestDto,userNameToken);
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<DefaultRes> deleteComment(HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }
            String userNameToken = claims.getSubject();
            commentService.deleteComment(commentId,id,userNameToken);
            defaultRes.setStatusCode(StatusCode.OK);
            defaultRes.setResponseMessage(ResponseMessage.DELETE_SUCCESS);
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }
}
