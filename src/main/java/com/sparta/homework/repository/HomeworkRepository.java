package com.sparta.homework.repository;

import com.sparta.homework.entity.Homework;
import com.sparta.homework.repository.mapping.PasswordMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findAllByOrderByCreatedAtDesc();
    PasswordMapping getPasswordById(Long id);
    List<Homework> findAllById(Long id);
}
