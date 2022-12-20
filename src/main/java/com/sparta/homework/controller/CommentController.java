package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{id}")
@Api(tags = {"댓글 CRUD api"})
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final DefaultRes defaultRes;
    @PostMapping("/comment")
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성한다.")
    public CommentRequestDto addComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id) {
        String token = jwtUtil.resolveToken(request);
        String userNameToken = jwtUtil.isValidToken(token);
        return commentService.addComment(requestDto, userNameToken, id);
        }

    @PutMapping("/comment/{commentId}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    public CommentRequestDto updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        String userNameToken = jwtUtil.isValidToken(token);
        return commentService.updateComment(commentId,id,requestDto,userNameToken);
        }
    @DeleteMapping("/comment/{commentId}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    public ResponseEntity<DefaultRes> deleteComment(HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        String userNameToken = jwtUtil.isValidToken(token);
        commentService.deleteComment(commentId,id,userNameToken);
        return defaultRes.deleteOK();
        }
}
