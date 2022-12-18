package com.sparta.homework.dto;



import lombok.Getter;

import java.time.LocalDate;
@Getter
public class CommentResponseDto {
    private LocalDate createdAt;
    private String username;
    private String comment;

    public CommentResponseDto(LocalDate createdAt, String username, String comment) {
        this.createdAt = createdAt;
        this.username = username;
        this.comment = comment;
    }
}
