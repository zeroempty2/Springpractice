package com.sparta.homework.repository;

import com.sparta.homework.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findAllByOrderByCreatedAtDesc();
}
