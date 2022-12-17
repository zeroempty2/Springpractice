package com.sparta.homework.dto;

import com.sparta.homework.repository.mapping.Comments;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class HomeworkResponseByIdDto {
    private String contents;
    private String title;
    private LocalDate createdAt;
    private String username;
    private List<Comments> comments;

    public HomeworkResponseByIdDto(String title, String contents, LocalDate createdAt, String username, List<Comments> comments) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.username = username;
        this.comments = comments;
    }
}
