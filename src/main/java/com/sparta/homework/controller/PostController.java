package com.sparta.homework.controller;

import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.dto.PostResponseDto;
import com.sparta.homework.exception.InvalidTokenException;
import com.sparta.homework.exception.NotLoginException;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.service.PostService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final DefaultRes defaultRes;
    private final JwtUtil jwtUtil;

    @PostMapping("/post")
    public PostRequestDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new InvalidTokenException();
            }
            String userNameToken = claims.getSubject();
            return postService.createPost(requestDto, userNameToken);
        }
        throw new NotLoginException();
    }
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return  postService.getPosts();
    }
    @GetMapping("/post/{id}")
    public PostResponseDto getSelectPosts(@PathVariable Long id){
       return postService.getSelectPosts(id);
    }
    @PutMapping("/post/{id}")
    public PostRequestDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new InvalidTokenException();
            }
            String userNameToken = claims.getSubject();
            return postService.update(id,requestDto,userNameToken);
        }
        throw new NotLoginException();
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<DefaultRes> deletePost(@PathVariable Long id, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new InvalidTokenException();
            }
            String userNameToken = claims.getSubject();
            postService.delete(id,userNameToken);
            return defaultRes.deletePostOk();
        }
        throw new NotLoginException();
    }
}

