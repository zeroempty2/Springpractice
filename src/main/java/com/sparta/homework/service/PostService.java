package com.sparta.homework.service;

import com.sparta.homework.dto.CommentResponseDto;
import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.dto.PostResponseDto;
import com.sparta.homework.entity.Comment;
import com.sparta.homework.entity.Post;
import com.sparta.homework.entity.User;
import com.sparta.homework.exception.exceptions.InvalidWriterException;
import com.sparta.homework.exception.exceptions.NotFoundPostException;
import com.sparta.homework.exception.exceptions.NotFoundUserException;
import com.sparta.homework.repository.PostRepository;
import com.sparta.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostRequestDto createPost(PostRequestDto requestDto, String userInfo) {
            User user = userRepository.findByUsername(userInfo).orElseThrow(NotFoundUserException::new);

            postRepository.saveAndFlush(new Post(requestDto, user));
            return requestDto;
        }


    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        List<PostResponseDto> postResponse = new ArrayList<>();

        for (Post response : posts){
            List<CommentResponseDto> comments = new ArrayList<>();
            List<Comment> commentList = response.getComments();

            for(Comment comment : commentList){
                comments.add(comment.getResponseComment(comment));
            }
            postResponse.add(response.getResponsePost(response,comments));
        }
        return postResponse;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getSelectPosts(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundPostException::new);

        List<CommentResponseDto> comments = new ArrayList<>();
        List<Comment> commentList = post.getComments();
        for(Comment comment : commentList){
            comments.add(comment.getResponseComment(comment));
        }
        return post.getResponsePost(post,comments);
    }

    @Transactional
    public PostRequestDto update(Long id, PostRequestDto requestDto, String userInfo) {

            User user = userRepository.findByUsername(userInfo).orElseThrow(NotFoundUserException::new);

            Post post = postRepository.findById(id).orElseThrow(NotFoundPostException::new);

            if(user.isADMIN()){
                post.update(requestDto);
                return requestDto;
            }
            else if (post.isWriter(user.getId())) {
                post.update(requestDto);
                return requestDto;
            } else {
                throw new InvalidWriterException();
            }
        }


    @Transactional
    public void delete(Long id, String userInfo) {
            User user = userRepository.findByUsername(userInfo).orElseThrow(NotFoundUserException::new);

            Post post = postRepository.findById(id).orElseThrow(NotFoundPostException::new);

            if(user.isADMIN()){
                postRepository.deleteById(id);
            }
            else if (post.isWriter(user.getId())) {
                postRepository.deleteById(id);
            } else {
                throw new InvalidWriterException();
            }
        }
}

