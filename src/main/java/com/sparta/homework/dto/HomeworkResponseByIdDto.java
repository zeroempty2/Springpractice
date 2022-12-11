package com.sparta.homework.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HomeworkResponseByIdDto {
    private String username;
    private String contents;
    private String title;
    private LocalDate createdAt;

    public HomeworkResponseByIdDto(String username, String contents, String title, LocalDate createdAt) {
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.createdAt = createdAt;
    }
}
