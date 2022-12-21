package com.sparta.homework.service;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.entity.Comment;
import com.sparta.homework.entity.Post;
import com.sparta.homework.entity.User;
import com.sparta.homework.exception.exceptions.InvalidWriterException;
import com.sparta.homework.exception.exceptions.NotFoundCommentException;
import com.sparta.homework.exception.exceptions.NotFoundPostException;
import com.sparta.homework.exception.exceptions.NotFoundUserException;
import com.sparta.homework.repository.CommentRepository;
import com.sparta.homework.repository.PostRepository;
import com.sparta.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@RequiredArgsConstructor

public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentRequestDto addComment(CommentRequestDto requestDto,String userNameToken,Long id) {
           User user = userRepository.findByUsername(userNameToken).orElseThrow(NotFoundUserException::new);

            Post post = postRepository.findById(id).orElseThrow(NotFoundPostException::new);

            Comment comment = new Comment(requestDto,user,user.getUsername(), post);
            commentRepository.save(comment);
            return requestDto;
    }

    @Transactional
    public CommentRequestDto updateComment(Long commentId, Long id, CommentRequestDto requestDto, String userNameToken) {
            User user = userRepository.findByUsername(userNameToken).orElseThrow(NotFoundUserException::new);

            postRepository.findById(id).orElseThrow(NotFoundPostException::new);

            Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);

            if(user.isADMIN()){
                comment.update(requestDto);
                return requestDto;
            }
            else if (comment.isWriter(user.getId())) {
                comment.update(requestDto);
                return requestDto;
            } else {
                throw new InvalidWriterException();
            }
        }
    @Transactional
    public void deleteComment(Long commentId, Long id, String userNameToken) {
            User user = userRepository.findByUsername(userNameToken).orElseThrow(NotFoundUserException::new);

            postRepository.findById(id).orElseThrow(NotFoundPostException::new);

            Comment comment = commentRepository.findByIdAndPost_Id(commentId,id).orElseThrow(NotFoundCommentException::new);

            if(user.isADMIN()){
                commentRepository.delete(comment);
            }
            else if (comment.isWriter(user.getId())) {
                commentRepository.delete(comment);
            } else {
                throw new InvalidWriterException();
            }
        }
    }



