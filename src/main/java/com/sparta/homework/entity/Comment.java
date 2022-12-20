package com.sparta.homework.entity;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.dto.CommentResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
// HOMEWORK 엔티티에서 mappedby "homework" 해줄시 HOMEWORK_COMMENTS 테이블 생성되지 않음
    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;


    public Comment(CommentRequestDto commentRequestDto, User user, String username, Post post){
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.username = username;
        this.post = post;
    }
    public CommentResponseDto getResponseComment(Comment comment){
        return new CommentResponseDto(comment.getCreatedAt(),comment.getModifiedAt(),comment.getUsername(),comment.getComment());
    }
    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

    public boolean isWriter(Long userId){
        return getUser().getId().equals(userId);
    }
}
