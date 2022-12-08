package com.sparta.homework.dto;

import com.sparta.homework.entity.Homework;
import com.sparta.homework.repository.mapping.HomeworkMapping;
import lombok.Getter;
import org.apache.coyote.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HomeworkResponseDto {
    private List<Homework> homework;

    public HomeworkResponseDto(List<Homework> homework) {
        this.homework = homework;
    }
}

