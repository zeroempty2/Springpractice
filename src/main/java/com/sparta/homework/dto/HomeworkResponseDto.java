package com.sparta.homework.dto;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class HomeworkResponseDto {
    private String contents;
    private String title;
    private LocalDate createdAt;
    private String username;

    public HomeworkResponseDto(String contents, String title, LocalDate createdAt,String username) {
        this.contents = contents;
        this.title = title;
        this.createdAt = createdAt;
        this.username = username;
    }
}

