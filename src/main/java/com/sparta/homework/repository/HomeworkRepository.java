package com.sparta.homework.repository;

import com.sparta.homework.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findAllByOrderByCreatedAtDesc();

    Optional<Homework> findByIdAndUserId(Long id, Long userId);
}
