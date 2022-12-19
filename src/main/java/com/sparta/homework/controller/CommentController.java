package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.exception.InvalidTokenException;
import com.sparta.homework.exception.NotLoginException;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.service.CommentService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
                throw new InvalidTokenException();
            }
            String userNameToken = claims.getSubject();
            return commentService.addComment(requestDto, userNameToken, id);
        }
        throw new NotLoginException();
    }
    @PutMapping("/comment/{commentId}")
    public CommentRequestDto updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new InvalidTokenException();
            }
            String userNameToken = claims.getSubject();
            return commentService.updateComment(commentId,id,requestDto,userNameToken);
        }
        throw new NotLoginException();
    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<DefaultRes> deleteComment(HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new InvalidTokenException();
            }
            String userNameToken = claims.getSubject();
            commentService.deleteComment(commentId,id,userNameToken);
            return defaultRes.deleteOK();
        }
        throw new NotLoginException();
    }
}
