package com.sparta.homework.service;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.entity.Comment;
import com.sparta.homework.entity.Homework;
import com.sparta.homework.entity.User;
import com.sparta.homework.repository.CommentRepository;
import com.sparta.homework.repository.HomeworkRepository;
import com.sparta.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@RequiredArgsConstructor

public class CommentService {
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentRequestDto addComment(CommentRequestDto requestDto,String userNameToken,Long id) {
           User user = userRepository.findByUsername(userNameToken).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Homework homework = homeworkRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
            );

            Comment comment = new Comment(requestDto,user,user.getUsername(),homework);
            commentRepository.saveAndFlush(comment);
            return requestDto;
    }

    @Transactional
    public CommentRequestDto updateComment(Long commentId, Long id, CommentRequestDto requestDto, String userNameToken) {
            User user = userRepository.findByUsername(userNameToken).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 코멘트가 존재하지 않습니다.")
            );

            if(user.isADMIN()){
                comment.update(requestDto);
                return requestDto;
            }
            else if (comment.isWriter(user.getId())) {
                comment.update(requestDto);
                return requestDto;
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
    @Transactional
    public void deleteComment(Long commentId, Long id, String userNameToken) {
            User user = userRepository.findByUsername(userNameToken).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            homeworkRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 게시글이 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findByIdAndHomework_Id(commentId,id).orElseThrow(
                    () -> new NullPointerException("유효한 계정이 아니거나 코멘트가 존재하지 않습니다.")
            );
            if(user.isADMIN()){
                commentRepository.delete(comment);
            }
            else if (comment.isWriter(user.getId())) {
                commentRepository.delete(comment);
            } else {
                throw new IllegalArgumentException("유효한 계정이 아닙니다");
            }
        }
    }



