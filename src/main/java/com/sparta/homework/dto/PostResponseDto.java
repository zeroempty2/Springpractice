package com.sparta.homework.dto;

import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter
public class PostResponseDto {
    private String contents;
    private String title;
    private LocalDate createdAt;
    private String username;

    List<CommentResponseDto> comments;

    public PostResponseDto(String contents, String title, LocalDate createdAt, String username, List<CommentResponseDto> comments) {
        this.contents = contents;
        this.title = title;
        this.createdAt = createdAt;
        this.username = username;
        this.comments = comments;
    }
}

