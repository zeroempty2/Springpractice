package com.sparta.homework.entity;

import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.repository.CommentRepository;
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

    public Comment(CommentRequestDto commentRequestDto, User user,String username){
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.username = username;
    }

}
