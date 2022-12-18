package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.responseMessageData.DefaultRes;
import com.sparta.homework.responseMessageData.ResponseMessage;
import com.sparta.homework.responseMessageData.StatusCode;
import com.sparta.homework.service.CommentService;
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

    private final DefaultRes defaultRes;
    @PostMapping("/comment")
    public CommentRequestDto addComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id){
        return commentService.addComment(requestDto,request,id);
    }
    @PutMapping("/comment/{commentId}")
    public CommentRequestDto updateComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        return commentService.updateComment(commentId,id,requestDto,request);
    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<DefaultRes> deleteComment(HttpServletRequest request, @PathVariable Long id, @PathVariable Long commentId){
        commentService.deleteComment(commentId,id,request);
        defaultRes.setStatusCode(StatusCode.OK);
        defaultRes.setResponseMessage(ResponseMessage.DELETE_SUCCESS);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }
}
