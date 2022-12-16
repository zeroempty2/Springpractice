package com.sparta.homework.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HomeworkResponseByIdDto {
    private String contents;
    private String title;
    private LocalDate createdAt;
    private String username;

    public HomeworkResponseByIdDto(String title, String contents, LocalDate createdAt, String username) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.username = username;
    }
}
