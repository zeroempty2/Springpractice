package com.sparta.homework.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class PostResponseByIdDto {
    private String contents;
    private String title;
    private LocalDate createdAt;
    private String username;
    List<CommentResponseDto> comments;
//    private List<Comments> comments;

    public PostResponseByIdDto(String title, String contents, LocalDate createdAt, String username, List<CommentResponseDto> comments){
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.username = username;
        this.comments = comments;
    }
}
