package com.sparta.homework.entity;

import com.sparta.homework.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "HOMEWORK_ID", nullable = false)
    private Homework homework;


    public Comment(CommentRequestDto commentRequestDto, User user,String username,Homework homework){
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.username = username;
        this.homework = homework;
    }
    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

}
