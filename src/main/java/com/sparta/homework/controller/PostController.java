package com.sparta.homework.controller;

import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.dto.PostResponseDto;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = {"게시글 CRUD api"})
public class PostController {
    private final PostService postService;
    private final DefaultRes defaultRes;
    private final JwtUtil jwtUtil;

    @PostMapping("/post")
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다.")
    public PostRequestDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        String userInfo = jwtUtil.isValidToken(token);
        return postService.createPost(requestDto, userInfo);
        }

    @GetMapping("/posts")
    @ApiOperation(value = "전체 게시글 불러오기", notes = "전체 게시글을 불러온다")
    public List<PostResponseDto> getPosts(){
        return  postService.getPosts();
    }
    @GetMapping("/post/{id}")
    @ApiImplicitParam(name = "id", value = "게시글 id")
    @ApiOperation(value = "게시글 불러오기", notes = "선택한 게시글을 불러온다")
    public PostResponseDto getSelectPosts(@PathVariable Long id){
        return postService.getSelectPosts(id);
    }
    @PutMapping("/post/{id}")
    @ApiImplicitParam(name = "id", value = "게시글 id")
    @ApiOperation(value = "게시글 수정", notes = "선택한 게시글을 수정한다")
    public PostRequestDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        String userInfo = jwtUtil.isValidToken(token);
        return postService.update(id,requestDto,userInfo);
        }
    @DeleteMapping("/post/{id}")
    @ApiImplicitParam(name = "id", value = "게시글 id")
    @ApiOperation(value = "게시글 삭제", notes = "선택한 게시글을 삭제한다")
    public ResponseEntity<DefaultRes> deletePost(@PathVariable Long id, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        String userInfo = jwtUtil.isValidToken(token);
        postService.delete(id,userInfo);
        return defaultRes.deletePostOk();
        }
}

