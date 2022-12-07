package com.sparta.homework.repository;

import com.sparta.homework.entity.Homework;
import com.sparta.homework.repository.mapping.HomeworkMapping;
import com.sparta.homework.repository.mapping.PasswordMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findAll();
    List<HomeworkMapping> findAllById(Long id);

    PasswordMapping getPasswordById(Long id);
}
