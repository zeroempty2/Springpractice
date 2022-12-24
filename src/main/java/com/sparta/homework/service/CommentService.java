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
    public CommentRequestDto addComment(CommentRequestDto requestDto,String userInfo,Long id) {
           User user = userRepository.findByUsername(userInfo).orElseThrow(NotFoundUserException::new);

            Post post = postRepository.findById(id).orElseThrow(NotFoundPostException::new);

            Comment comment = new Comment(requestDto,user,post);
            commentRepository.save(comment);
            return requestDto;
    }

    @Transactional
    public CommentRequestDto updateComment(Long commentId, Long id, CommentRequestDto requestDto, String userInfo) {
            User user = userRepository.findByUsername(userInfo).orElseThrow(NotFoundUserException::new);

            postRepository.findById(id).orElseThrow(NotFoundPostException::new);

            Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);

            if (comment.isWriter(user)) {
                comment.update(requestDto);
                return requestDto;
            } else {
                throw new InvalidWriterException();
            }
        }
    @Transactional
    public CommentRequestDto AdminUpdateComment(Long commentId, Long id, CommentRequestDto requestDto) {
        postRepository.findById(id).orElseThrow(NotFoundPostException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
        comment.update(requestDto);
        return requestDto;
    }
    @Transactional
    public void deleteComment(Long commentId, Long id, String userInfo) {
            User user = userRepository.findByUsername(userInfo).orElseThrow(NotFoundUserException::new);
            postRepository.findById(id).orElseThrow(NotFoundPostException::new);
            Comment comment = commentRepository.findByIdAndPost_Id(commentId,id).orElseThrow(NotFoundCommentException::new);
          if (comment.isWriter(user)) {
                commentRepository.delete(comment);
            } else {
                throw new InvalidWriterException();
            }
        }
    @Transactional
    public void adminDeleteComment(Long commentId, Long id) {
        postRepository.findById(id).orElseThrow(NotFoundPostException::new);
        Comment comment = commentRepository.findByIdAndPost_Id(commentId,id).orElseThrow(NotFoundCommentException::new);
        commentRepository.delete(comment);
    }
}




