package com.sparta.homework.dto;

import com.sparta.homework.entity.Homework;
import lombok.Getter;

import java.util.List;

@Getter
public class HomeworkResponseDto {
    private List<Homework> homework;

    public HomeworkResponseDto(List<Homework> homework) {
        this.homework = homework;
    }
}

