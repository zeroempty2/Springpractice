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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;


    public Comment(CommentRequestDto commentRequestDto, User user, Post post){
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.post = post;
    }
    public CommentResponseDto getResponseComment(Comment comment){
        return new CommentResponseDto(comment.getCreatedAt(),comment.getModifiedAt(),comment.getUser().getUsername(),comment.getComment());
    }
    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

    public boolean isWriter(Long userId){
        return getUser().getId().equals(userId);
    }
}
