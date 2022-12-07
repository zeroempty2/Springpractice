package com.sparta.homework.repository.mapping;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
public interface HomeworkMapping {
    String getUsername();
    String getContents();
    String getTitle();
    LocalDate getCreatedAt();


}
