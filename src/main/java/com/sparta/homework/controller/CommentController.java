package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.exception.exceptions.IsNotAdminTokenException;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.responseMessageData.ResponseMessageService;
import com.sparta.homework.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    private final ResponseMessageService responseMessageService;
    @PostMapping("/comments")
    @ApiImplicitParam(name = "id", value = "게시글 id",dataTypeClass = Integer.class)
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성한다.")
    public CommentRequestDto addComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id) {
        String token = jwtUtil.resolveToken(request);
        String userInfo = jwtUtil.isValidToken(token);
        return commentService.addComment(requestDto, userInfo, id);
        }

    @PutMapping("/comments/{commentId}")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "게시글 id",dataTypeClass = Integer.class),@ApiImplicitParam(name = "commentId", value = "댓글 id",dataTypeClass = Integer.class)})
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    public CommentRequestDto updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        String userInfo = jwtUtil.isValidToken(token);
        return commentService.updateComment(commentId,id,requestDto,userInfo);
        }
    @PutMapping("/admin/{commentId}")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "게시글 id",dataTypeClass = Integer.class),@ApiImplicitParam(name = "commentId", value = "댓글 id",dataTypeClass = Integer.class)})
    @ApiOperation(value = "댓글 수정(관리자)", notes = "댓글을 관리자 권한으로 유저 확인 없이 수정한다.")
    public CommentRequestDto AdminUpdateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        if(jwtUtil.isAdminToken(token)){
            return commentService.AdminUpdateComment(commentId,id,requestDto);
        }
        throw new IsNotAdminTokenException();
    }
    @DeleteMapping("/comments/{commentId}")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "게시글 id",dataTypeClass = Integer.class),@ApiImplicitParam(name = "commentId", value = "댓글 id",dataTypeClass = Integer.class)})
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    public ResponseEntity<DefaultRes> deleteComment(HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        String userInfo = jwtUtil.isValidToken(token);
        commentService.deleteComment(commentId,id,userInfo);
        return responseMessageService.deleteOk();
        }
    @DeleteMapping("/admin/{commentId}")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "게시글 id",dataTypeClass = Integer.class),@ApiImplicitParam(name = "commentId", value = "댓글 id",dataTypeClass = Integer.class)})
    @ApiOperation(value = "댓글 삭제(관리자)", notes = "댓글을 관리자 권한으로 유저 확인 없이 삭제한다.")
    public ResponseEntity<DefaultRes> AdminDeleteComment(HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        String token = jwtUtil.resolveToken(request);
        if(jwtUtil.isAdminToken(token)) {
            commentService.adminDeleteComment(commentId, id);
            return responseMessageService.deleteOk();
        }
        throw new IsNotAdminTokenException();
    }
}
