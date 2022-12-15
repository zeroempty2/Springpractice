package com.sparta.homework.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HomeworkResponseByIdDto {
    private String contents;
    private String title;
    private LocalDate createdAt;

    public HomeworkResponseByIdDto(String title, String contents, LocalDate createdAt) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
