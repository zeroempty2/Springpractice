package com.sparta.homework.repository;

import com.sparta.homework.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndHomework_Id(Long id, Long homeworkId);
}
