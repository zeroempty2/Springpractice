package com.sparta.homework.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HomeworkResponseByIdDto {
    private String title;
    private LocalDate createdAt;

    public HomeworkResponseByIdDto(String title, LocalDate createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }
}
