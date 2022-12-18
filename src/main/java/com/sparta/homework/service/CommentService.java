package com.sparta.homework.service;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.entity.Comment;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.entity.User;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.CommentRepository;
import com.sparta.homework.repository.HomeworkRepository;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.homework.entity.UserRoleEnum.ADMIN;


@Service
@RequiredArgsConstructor

public class CommentService {
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentRequestDto addComment(CommentRequestDto requestDto,HttpServletRequest request,Long id) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {

                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
            );

            Comment comment = new Comment(requestDto,user,user.getUsername(),homework);
            commentRepository.saveAndFlush(comment);
            return requestDto;
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }

    @Transactional
    public CommentRequestDto updateComment(Long commentId, Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );


            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 코멘트가 존재하지 않습니다.")
            );

            if(user.getRole().equals(ADMIN)){
                comment.update(requestDto);
                return requestDto;
            }
            else if (comment.getUser().getId().equals(user.getId())) {
                comment.update(requestDto);
                return requestDto;
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
        throw new IllegalArgumentException("로그인 해 주십시오");
    }
    @Transactional
    public void deleteComment(Long commentId, Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findByIdAndHomework_Id(commentId,id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 코멘트가 존재하지 않습니다.")
            );
            System.out.println(comment);
            if(user.getRole().equals(ADMIN)){
                commentRepository.delete(comment);
            }
            else if (comment.getUser().getId().equals(user.getId())) {
                commentRepository.delete(comment);
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
    }
}


